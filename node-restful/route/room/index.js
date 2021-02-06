var express = require('express');
var router = express.Router();
var connection = require('./../../mysql');
const auth = require('./../../middlewares/auth.js')

router.post('/', auth, (req, res) => {
    const uid = req.uid
    let {name, image_url, max_num, tags} = req.body
    if (image_url == undefined) {
        image_url = "https://itplace.s3.ap-northeast-2.amazonaws.com/land_scape.png"
    }
    let sql = `
    INSERT INTO room (name, landscape_url, max_num, tag, host_uid) 
    VALUES ('${name}', '${image_url}', ${max_num}, '${tags}', '${uid}')
    `
    connection.query(sql, (err, row, fields) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 SQL Server Error')
        }
        let roomId = row.insertId
        let sql = `INSERT INTO user_room VALUES ('${uid}', ${roomId})`
        connection.query(sql, (err, row, fields) => {
            if (err){ 
                console.error(err.stack)
            }
            console.log("USER_ROOM INSERTED OK")
        })
        var response = {
            rid: roomId 
        }
        res.send(response)
    })
})

router.post('/albums/:rid', (req, res) => {
    let {image_url} = req.body
    let rid = req.params.rid 
    let sql = `
        INSERT INTO album
        VALUES (${image_url}, ${rid})
    `;
    connection.query(sql, (err, row, fields) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 SQL Server Error')
        }
        res.send("album Insert OK")
    })
})

router.get('/albums/:rid', (req, res) => {
    let rid = req.params.rid
    let sql = `
        SELECT image_url
        FROM album
        WHERE rid = ${rid}
    `
    connection.query(sql, (error, rows, fields) => {
        if (error){
            console.error(error.stack)
            res.status(500).send('500 Server Error')
        }
        for (row of rows){
            let result = row
            result['current_num'] = 2
            // result['user_thumbnail_url'] = "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
            console.log(result)
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        console.log(result)
        res.send(result)
    })
})

router.get('/', auth, (req, res) => {
    const uid = req.uid
    sql = `
        SELECT r.name, r.max_num, r.tag, r.landscape_url, u.profile_url
        FROM room as r, user as u
        WHERE host_uid = uid AND r.rid in (
            SELECT ur.rid
            FROM user_room as ur
            WHERE ur.uid = '${uid}'
        );
    `;
    connection.query(sql, (error, rows, fields) => {
        if (error){
            console.error(error.stack)
            res.status(500).send('500 Server Error')
        }
        for (row of rows){
            let result = row
            result['current_num'] = 2
            // result['user_thumbnail_url'] = "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
            console.log(result)
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        console.log(result)
        res.send(result)
    })
})

router.get('/all', (req, res) => {
    connection.query(`
    SELECT r.name, r.max_num, r.tag, r.landscape_url, u.profile_url  
    from room as r, user as u 
    where host_uid = uid`
    , (error, rows, fields) => {
        if (error){
            console.error(error.stack)
            res.status(500).send('500 Server Error')
        }
        for (row of rows){
            let result = row
            result['current_num'] = 2
            // result['user_thumbnail_url'] = "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
            console.log(result)
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        console.log(result)
        res.send(result)
    })
})

router.get('/search/:txt', (req, res) => {
    let search = req.txt
    console.log(search)
    connection.query(`
    SELECT r.name, r.max_num, r.tag, r.landscape_url, u.profile_url  
    FROM room as r, user as u 
    WHERE host_uid = uid and (r.name LIKE '%${search}%' or r.tag LIKE '%${search}%')`, (error, rows, fields) => {
        if (error){
            console.error(error.stack)
            res.status(500).send('500 Server Error')
        }
        for (row of rows){
            let result = row
            result['current_num'] = 2
            // result['user_thumbnail_url'] = "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
            console.log(result)
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        console.log(result)
        res.send(result)
    })
})

module.exports = router
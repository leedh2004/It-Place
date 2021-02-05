var express = require('express');
var router = express.Router();
var connection = require('./../../mysql');

router.post('/', (req, res) => {
    const uid = req.headers.authorization.split('Bearer ')[1];
    console.log(uid)
    let {name, image_url, max_num, tags} = req.body
    console.log(image_url)
    if (image_url == undefined) {
        image_url = "https://itplace.s3.ap-northeast-2.amazonaws.com/land_scape.png"
    }
    let sql = `INSERT INTO room (name, landscape_url, max_num, tag, host_uid) VALUES ('${name}', '${image_url}', ${max_num}, '${tags}', '${uid}')`
    console.log(sql)
    connection.query(sql, (err, row, fields) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 Server Error')
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

router.get('/', (req, res) => {
    connection.query('SELECT r.name, r.max_num, r.tag, r.landscape_url, u.profile_url  from room as r, user as u where host_uid = uid', (error, rows, fields) => {
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
    let search = req.params.txt
    console.log(search)
    connection.query(`SELECT r.name, r.max_num, r.tag, r.landscape_url, u.profile_url  from room as r, user as u where host_uid = uid and (r.name LIKE '%${search}%' or r.tag LIKE '%${search}%')`, (error, rows, fields) => {
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
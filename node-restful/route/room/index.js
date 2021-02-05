var express = require('express');
var router = express.Router();
var connection = require('./../../mysql');

router.post('/', (req, res) => {
    const uid = req.headers.authorization.split('Bearer ')[1];
    console.log(uid)
    let {name, image_url, max_num, tags} = req.body
    console.log(tags.split('#'))
    let sql = `INSERT INTO room (name, landscape_url, max_num) VALUES ('${name}', '${image_url}', ${max_num})`
    console.log(sql)
    connection.query(sql, (err, row, fields) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 Server Error')
        }
        let roomId = row.insertId
        tags = tags.replace(/ /gi, "")
        tags = tags.split('#')
        console.log(tags)
        for (var tag of tags) {
            console.log(tag)
            if (tag.length > 0) {
                let sql = `INSERT INTO tag VALUES ('${tag}', ${roomId})`;
                connection.query(sql, (err, row, fields) => {
                    if (err){
                        console.error(err.stack)
                    }
                    else console.log("TAG INSERTED OK")
                })
            }
        }
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
    connection.query('SELECT * from room', (error, rows, fields) => {
        if (error){
            console.log("error")
            res.status(500).send('500 Server Error')
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        console.log(result)
        res.send(result)
    })
})

module.exports = router
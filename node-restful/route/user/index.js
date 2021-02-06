var express = require('express');
var router = express.Router();
var connection = require('./../../mysql')
// DB INSERT TEST 완료
// connection.query('INSERT INTO `user` VALUES (2, "도현") ', (error, rows, fields) => {
//     if (error) {
//     console.log("error")
//     }
//     console.log('INSERT', rows)
// })

router.post('/', (req, res) => {
    let {uid, image_url, name} = req.body
    if (image_url == undefined){
        image_url = "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
    }
    connection.query(`INSERT INTO user VALUES ('${uid}', '${name}', '${image_url}')`, (err, row, fileds) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 Server Error')
        }
        console.log("SEND")
        res.status(200).send('Insert OK')
    })
})

router.get('/', (req, res) => {
    connection.query('SELECT * from user', (error, rows, fields) => {
        if (error){
            console.log("error")
            res.status(500).send('500 Server Error')
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        console.log(result)
        res.send(result)
    })
})

router.get('/enter/:rid', (req, res) => {
    const rid = req.params.rid
    const uid = req.headers.authorization.split('Bearer ')[1];
    if (rid == undefined || uid == undefined) {
        res.status(401).send("Unathorized!")
    }
    let sql = `
        INSERT INTO user_room
        VALUES (${uid}, ${rid})
    `
    connection.query(sql, (err, row, fileds) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 SQL Server Error')
        }
        res.status(200).send('user enter room Insert OK')
    });
})

module.exports = router
var express = require('express');
var router = express.Router();
var connection = require('./../../mysql')
const auth = require('./../../middlewares/auth.js')

router.post('/', (req, res) => {
    let {uid, image_url, name} = req.body
    if (image_url == undefined){
        image_url = "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
    }
    connection.query(`INSERT INTO user VALUES ('${uid}', '${name}', '${image_url}')`, (err, row, fileds) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send('500 Server Error')
        } else{
            console.log("SEND")
            res.status(200).send('Insert OK')
        }
    })
})

router.get('/', (req, res) => {
    connection.query('SELECT * from user', (error, rows, fields) => {
        if (error){
            console.log("error")
            res.status(500).send(err.stack)
        }else{
            const result = Object.values(JSON.parse(JSON.stringify(rows)));
            console.log(result)
            res.send(result)
        }
    })
})

router.get('/enter/:rid', auth, (req, res) => {
    const rid = req.params.rid
    const uid = req.uid

    let sql = `
        INSERT INTO user_room
        VALUES (${uid}, ${rid})
    `

    connection.query(sql, (err, row, fileds) => {
        if (err) {
            console.error(err.stack)
            res.status(500).send(err.stack)
        } else {
            res.status(200).send('user enter room Insert OK')
        }
    });
})

module.exports = router
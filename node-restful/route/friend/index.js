var express = require('express');
var router = express.Router();
var connection = require('./../../mysql');
const auth = require('./../../middlewares/auth.js')

router.get('/', auth, (req, res) => {
    let uid = req.uid
    let sql = `
        SELECT *
        FROM friend
        WHERE from = ${uid} 
    `
    connection.query(sql, (error, rows, fields) => {
        if (error){
            console.error(error.stack)
            res.status(500).send('500 Server Error')
        }
        const result = Object.values(JSON.parse(JSON.stringify(rows)));
        res.send(result)
    })
})

module.exports = router

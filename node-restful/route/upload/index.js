const express = require('express');
const router = express.Router();
const path = require('path') 

const upload = require('./../../s3')

router.post('/', upload.single("imgFile"), function(req, res, next){
    let response = {
        'image_url':req.file.location
    }
    console.log(response)
    res.send(response)
})

module.exports = router

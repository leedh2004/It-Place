
require('dotenv').config()

const AWS = require("aws-sdk");
const multer = require("multer");
const multerS3 = require('multer-s3');
const path = require("path");

const s3 = new AWS.S3({
    accessKeyId: process.env.AWS_ACCESS_KEY_ID,
    secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY,
    region: process.env.AWS_REGION
});

const upload = multer({
    storage: multerS3({
        s3: s3,
        bucket: "itplace",
        key: function (req, file, cb) {
            let extension = path.extname(file.originalname);
            cb(null, Date.now().toString() + extension)
            // var filename = req.params.imageName;
            // var ext = file.mimetype.split('/')[1];
            // if(!['png', 'jpg', 'jpeg', 'gif', 'bmp'].includes(ext)) {
            //     return cb(new Error('Only images are allowed'));
            // }
            // cb(null, filename + '.jpg');
        },
        acl: 'public-read-write',
    })
})

module.exports = upload
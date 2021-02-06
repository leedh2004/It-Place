const express = require('express');
const morgan = require('morgan');
const path = require('path');
const nunjucks = require('nunjucks');
var fs = require('fs');
require('dotenv').config();

const port = process.env.PORT

const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);

app.use(morgan('dev'));

app.use('/node_modules', express.static(path.join(__dirname, 'node_modules')));
app.use('/static', express.static(path.join(__dirname, 'static')));

const homeRouter = require('./routers/home.js');

nunjucks.configure('views', {
    express: app,
    watch: true
});

app.set('view engine', 'html');

app.use('/', homeRouter);

io.on('connection', (socket) => {
    console.log('a user connected');
    socket.on('disconnect', () => {
        console.log('user disconnected');
    });
    socket.on('chat message', (msg) => {
        io.emit('chat message', msg);
    });
});


const multer = require('multer');
// 기타 express 코드
const upload = multer({
    storage: multer.diskStorage({
        destination: function (req, file, cb) {
            cb(null, 'face/');
        },
        filename: function (req, file, cb) {
            cb(null, new Date().valueOf() + path.extname(file.originalname));
        }
    }),
});

app.post('/face', upload.single('img'), function (req, res) {
    console.log(req.file)

    var request = require('request');
    var api_url = 'https://openapi.naver.com/v1/vision/celebrity'; // 유명인 인식
    // var api_url = 'https://openapi.naver.com/v1/vision/face'; // 얼굴 감지

    var _formData = {
        image: req.file
    };

    var _req = request.post({
        url: api_url, formData: _formData,
        headers: { 'X-Naver-Client-Id': '3HXelR8u8GYSmfcUojjc', 'X-Naver-Client-Secret': 'WBgFHXMwl6' }
    }).on('response', function (response) {
        console.log(response.statusCode) // 200
        console.log(response.headers['content-type'])
    });

    console.log(request.head);
    _req.pipe(res);
    // 브라우저로 출력
});

http.listen(port, () => { console.log('server running'); });
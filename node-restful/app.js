require('dotenv').config()

const express = require('express')
const app = express()
const port = process.env.SERVER_PORT
const path = require('path')
var fs = require('fs')
var client_id = process.env.CLOVA_CLIENT_ID
var client_secret = process.env.CLOVA_SECRET_KEY

var userRoute = require('./route/user')
var uploadRoute = require('./route/upload')
var roomRoute = require('./route/room')

var morgan = require('morgan')

// !!! .env 파일 root folder에 생성할 것 !!! .env 파일 notion 참고
var connection = require('./mysql')
// 라우팅 테스트
// express built in bodyParser

app.use(express.json())
app.use(express.urlencoded({ extended: true }))
// logging
app.use(morgan('tiny'))

// 정적이미지 제공
// 요청 url: /assets/test.jpg
app.use(express.static('public'))

// Routing 테스트
app.use('/user', userRoute)
app.use('/room', roomRoute)
// S3 Upload 테스트
app.use('/upload', uploadRoute)

app.get('/', (req, res) => {
  res.send('Hello World!')
})

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
    image: 'image',
    image: fs.createReadStream('./' + req.file.path) // FILE 이름
  };

  var _req = request.post({
    url: api_url, formData: _formData,
    headers: { 'X-Naver-Client-Id': client_id, 'X-Naver-Client-Secret': client_secret }
  }).on('response', function (response) {
    console.log(response.statusCode) // 200
    console.log(response.headers['content-type'])
  });

  console.log(request.head);
  _req.pipe(res);
  // 브라우저로 출력
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})


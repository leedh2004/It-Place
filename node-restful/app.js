require('dotenv').config()

const express = require('express')
const app = express()
const port = process.env.SERVER_PORT

var userRoute = require('./route/user')
var uploadRoute = require('./route/upload')
var roomRoute = require('./route/room')

var morgan = require('morgan')

// !!! .env 파일 root folder에 생성할 것 !!! .env 파일 notion 참고
var connection = require('./mysql')
// 라우팅 테스트
// express built in bodyParser

app.use(express.json())
app.use(express.urlencoded({extended:true}))
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

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})


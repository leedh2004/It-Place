const express = require('express')
const app = express()
const port = process.env.SERVER_PORT

var userRoute = require('./route/user')
var uploadRoute = require('./route/upload')
var morgan = require('morgan')

// !!! .env 파일 root folder에 생성할 것 !!! .env 파일 notion 참고
var connection = require('./mysql')

// DB 조회 TEST 완료
connection.query('SELECT * from user', (error, rows, fields) => {
  if (error){
    console.log("error")
  }
  console.log('User info is', rows)
})

// DB INSERT TEST 완료
/*
connection.query('INSERT INTO `user` VALUES (2, "Soryung") ', (error, rows, fields) => {
  if (error){
    console.log("error")
  }
  console.log('INSERT', rows)
})
*/

// 라우팅 테스트
// express built in bodyParser
app.use(express.json())

// logging
app.use(morgan('tiny'))

// 정적이미지 제공
// 요청 url: /assets/test.jpg
app.use(express.static('public'))

// Routing 테스트
app.use('/user', userRoute)

// S3 Upload 테스트
app.use('/upload', uploadRoute)

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})


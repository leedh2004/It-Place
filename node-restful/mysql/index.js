// Secret Config
require('dotenv').config()

const mysql = require('mysql') 

const connection = mysql.createConnection({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_INSTANCE
    })
connection.connect()
module.exports = connection
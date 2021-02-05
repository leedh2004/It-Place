const express = require('express');
const morgan = require('morgan');
const path = require('path');
const nunjucks = require('nunjucks');

require('dotenv').config();

const port = process.env.PORT

const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);

app.use(morgan('dev'));
app.use('/public', express.static(path.join(__dirname, 'public')));

app.use('/node_modules', express.static(path.join(__dirname, 'node_modules')));

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

http.listen(port, () => { console.log('server running'); });
const express = require('express');
const morgan = require('morgan');
const path = require('path');
const nunjucks = require('nunjucks');


const app = express();

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

app.listen(8080, () => { console.log('server running'); });
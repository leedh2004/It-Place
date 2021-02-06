
var connection = require('./../mysql');

module.exports = (req, res, next) => {
    const uid = req.headers.authorization.split('Bearer ')[1];
    sql = `
        SELECT *
        FROM user
        WHERE uid = '${uid}'
    `
    connection.query(sql, (error, rows, fields) => {
        console.log(rows.length);
        if (rows.length == 0) {
            next(Error("No Authorized User Token"))
        }
        req.uid = uid
        next()
    });

}
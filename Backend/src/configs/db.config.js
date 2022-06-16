const {Client} = require('pg');
const db = new Client({
    user: 'kemas_sbd',
    host: 'kemas-sbd.postgres.database.azure.com',
    database: 'moneymanager',
    password: 'grandier2*',
    port: 5432,
    sslmode: 'require',
    ssl: true
});

db.connect((err) => {
    if (err) {
        console.log(err);
        }
        else {
            console.log('Connected to the database');
        }
});

module.exports = db;
const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');
const app = express()

app.use(cors());
app.use(express.json());

const db = mysql.createConnection({
    host: "172.31.82.196",
    user: "G_37",
    password: "e008o0gPDe",
    database: "G_37_DB"
});

// Connect to MySQL
db.connect((err) => {
    if (err) {
        console.error('Error connecting to MySQL database:', err);
        return;
    }
    console.log('Connected to MySQL database');
});

app.post('/contactus', (req, res) => {
    console.log("Received form data:", req.body);
    const { name, email, message } = req.body;
    const sql = "INSERT INTO contactus (name, email, message) VALUES (?, ?, ?)";
    const values = [name, email, message];
    db.query(sql, values, (err, data) => {
        if (err) {
            console.error('Error inserting data:', err);
            return res.status(500).json({ error: 'Internal Server Error' });
        }
        return res.status(200).json({ message: 'Data inserted successfully' });
    });
});

app.listen(8081, ()=> {
    console.log("Listening on port 8081...")
});

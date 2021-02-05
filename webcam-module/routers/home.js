const { Router } = require('express');
const router = Router();

router.get('/', async (req, res) => {
    res.render('index');
});

router.get('/meeting/:roomNumber', async (req, res) => {
    const { roomNumber } = req.params;
    var id = Math.random().toString(36).substr(2, 11);
    res.render('meeting', { roomNumber, id });
});

router.get('/meeting/:roomNumber/:id', async (req, res) => {
    const { roomNumber, id } = req.params;
    res.render('meeting', { roomNumber, id });
});

module.exports = router;
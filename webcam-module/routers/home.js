const { Router } = require('express');
const router = Router();

router.get('/', async (req, res) => {
    res.render('index');
});
router.get('/meeting/:roomNumber', async (req, res) => {
    const { roomNumber } = req.params;
    res.render('meeting', { roomNumber });
});

module.exports = router;
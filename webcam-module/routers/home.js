const { Router } = require('express');
const router = Router();

router.get('/', async (req, res) => {
    res.render('index');
});
router.get('/master', async (req, res) => {
    res.render('master');
});
router.get('/visiter', async (req, res) => {
    res.render('visiter');
});

module.exports = router;
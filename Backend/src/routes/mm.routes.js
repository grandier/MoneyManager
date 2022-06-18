const passport = require('passport');
const express = require('express');
const router = express.Router();
const mmController = require('../controllers/mm.controllers');

router.post('/login',passport.authenticate('local'), mmController.login);

router.post('/register', mmController.register);

router.get('/showuser', mmController.showUser);

router.delete('/deleteuser', mmController.deleteUser);

router.post('/insertcategoryincome', mmController.insertCategoryIncome);

router.post('/insertcategoryexpense', mmController.insertCategoryExpense);

router.get('/showcategoryincome', mmController.showCategoryIncome);

router.get('/showcategoryexpense', mmController.showCategoryExpense);

router.post('/insertincome', mmController.insertIncome);

router.post('/insertexpense', mmController.insertExpense);

router.get('/showincome', mmController.showIncome);

router.get('/showexpense', mmController.showExpense);

router.put('/updateincome', mmController.updateIncome);

router.put('/updateexpense', mmController.updateExpense);

router.delete('/deleteincome', mmController.deleteIncome);

router.delete('/deleteexpense', mmController.deleteExpense);

router.put('/updatecategoryincome', mmController.updateCategoryIncome);

router.put('/updatecategoryexpense', mmController.updateCategoryExpense);

module.exports = router;
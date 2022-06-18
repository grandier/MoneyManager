const mmService = require('../services/mm.services');


async function login(req,res){
    try{
        //const result = await mmService.login(req.body);
        res.json({message: 'success'});
    }catch(err){
        res.json(err);
    }
}

async function register(req,res){
    try{
        const result = await mmService.register(req.body);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function showUser(req,res){
    try{
        const result = await mmService.showUser(req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function deleteUser(req,res){
    try{
        const result = await mmService.deleteUser(req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function insertCategoryIncome(req,res){
    try{
        const result = await mmService.insertCategoryIncome(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function insertCategoryExpense(req,res){
    try{
        const result = await mmService.insertCategoryExpense(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function showCategoryIncome(req,res){
    try{
        const result = await mmService.showCategoryIncome(req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function showCategoryExpense(req,res){
    try{
        const result = await mmService.showCategoryExpense(req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function insertIncome(req,res){
    try{
        const result = await mmService.insertIncome(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function insertExpense(req,res){
    try{
        const result = await mmService.insertExpense(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function showIncome(req,res){
    try{
        const result = await mmService.showIncome(req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function showExpense(req,res){
    try{
        const result = await mmService.showExpense(req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function updateIncome(req,res){
    try{
        const result = await mmService.updateIncome(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function updateExpense(req,res){
    try{
        const result = await mmService.updateExpense(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function deleteIncome(req,res){
    try{
        const result = await mmService.deleteIncome(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function deleteExpense(req,res){
    try{
        const result = await mmService.deleteExpense(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function updateCategoryIncome(req,res){
    try{
        const result = await mmService.updateCategoryIncome(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

async function updateCategoryExpense(req,res){
    try{
        const result = await mmService.updateCategoryExpense(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

module.exports = {
    login,
    register,
    showUser,
    deleteUser,
    insertCategoryIncome,
    insertCategoryExpense,
    showCategoryIncome,
    showCategoryExpense,
    insertIncome,
    insertExpense,
    showIncome,
    showExpense,
    updateIncome, 
    updateExpense, 
    deleteIncome,
    deleteExpense,
    updateCategoryIncome, 
    updateCategoryExpense 
}
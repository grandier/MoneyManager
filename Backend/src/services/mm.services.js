const res = require('express/lib/response');
const { use } = require('passport/lib');
const db = require('../configs/db.config');
const helper = require('../utils/helper');

async function login (mm){
    const {username, password} = mm;
    const query = `SELECT * FROM users WHERE username = '${username}'`;
    const result = await db.query(query);
    if(result.rows.length === 0){
        return {
            message: 'User not found'
        }
    }else{
        const user = result.rows[0];
        if(user.password === password){
            return {
                message: 'Login successful',
                user
            }
        }else{
            return {
                message: 'Password is not correct'
            }
        }
    }
}

async function register (mm){
    const {username, password} = mm;
    const pass = await helper.hashPassword(password);
    const query = `INSERT INTO users (username, password) VALUES ('${username}', '${pass}')`;
    const result = await db.query(query);
    if(result.rowCount === 1){
        return {
            message: 'User Created'
        }
    }else{
        return{
            message: 'Error'
        } 
    }
}

async function showUser (user){
    if(user){
        const query = `SELECT * FROM users WHERE "User_Id" = '${user.User_Id}'`;
        const result = await db.query(query);
        if(result.rowCount === 1){
            return {
                message: 'User found',
                user : result.rows
            }
        }else{
            return {
                message: 'User not found'
            }
        }
    }else{
        return {
            message: 'User not found'
        }
    }
}

async function deleteUser (user){
    if(user){
        const query = `DELETE FROM users WHERE "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount === 1){
            return {
                message: 'User deleted'
            }
        }
        else{
            return {
                message: 'User not found'
            }
        }
    }
    else{
        return {
            message: 'USer not logged in'
        }
    }
}

async function insertCategoryIncome (mm, user){
    const {Jenis} = mm;
    if(user){
        const query = `INSERT INTO "Kategori_Pendapatan" ("Jenis", "User_Id") VALUES ('${Jenis}', '${user.User_Id}')`;
        const result = await db.query(query);
        if(result.rowCount === 1){
            return {
                message: 'Category for Income Created'
            }
        }else{
            return{
                message: 'Error'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function insertCategoryExpense (mm, user){
    const {Jenis} = mm;
    if(user){
        const query = `INSERT INTO "Kategori_Pengeluaran" ("Jenis", "User_Id") VALUES ('${Jenis}', '${user.User_Id}')`;
        const result = await db.query(query);
        if(result.rowCount === 1){
            return {
                message: 'Category for Expense Created'
            }
        }else{
            return{
                message: 'Error'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function showCategoryIncome (user){
    if(user){
        const query = `SELECT * FROM "Kategori_Pendapatan" WHERE "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount){
            return {
                message: 'Category Found',
                categoryincome : result.rows
            }
        }else{
            return{
                message: 'No Category Found'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function showCategoryExpense (user){
    if(user){
        const query = `SELECT * FROM "Kategori_Pengeluaran" WHERE "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: 'Category Found',
                categoryexpense : result.rows
            }
        }else{
            return{
                message: 'No Category Found'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function insertIncome (mm, user){
    const {Deskripsi, Jumlah, Tanggal, KatPend_Id} = mm;
    if(user){
        const query2 = `SELECT * FROM "Kategori_Pendapatan" WHERE "KatPend_Id" = ${KatPend_Id} AND "User_Id" = ${user.User_Id}`
        const result2 = await db.query(query2);
        console.log(result2.rowCount);
        if(result2.rowCount > 0){
            const query = `INSERT INTO "Pendapatan" ("Deskripsi", "Jumlah", "Tanggal", "User_Id", "KatPend_Id") VALUES ('${Deskripsi}', ${Jumlah}, '${Tanggal}', ${user.User_Id}, ${KatPend_Id})`;
            const result = await db.query(query);
            console.log(result.rows);
            if(result.rowCount > 0){
                return {
                    message: 'Income Created'
                }
            }else{
                return{
                    message: 'Error'
                } 
            }
        }
        else{
            return {
                message: 'Category not found'
            }
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function insertExpense (mm, user){
    const {Deskripsi, Jumlah, Tanggal, KatPeng_Id} = mm;
    if(user){
        const query2 = `SELECT * FROM "Kategori_Pengeluaran" WHERE "KatPeng_Id" = ${KatPeng_Id} AND "User_Id" = ${user.User_Id}`
        const result2 = await db.query(query2);
        if(result2.rowCount > 0){
            const query = `INSERT INTO "Pengeluaran" ("Deskripsi", "Jumlah", "Tanggal", "User_Id", "KatPeng_Id") VALUES ('${Deskripsi}', ${Jumlah}, '${Tanggal}', ${user.User_Id}, ${KatPeng_Id})`;
            const result = await db.query(query);
            console.log(result.rows);
            if(result.rowCount > 0){
                return {
                    message: 'Expense Created'
                }
            }else{
                return{
                    message: 'Error'
                } 
            }
        }
        else{
            return {
                message: 'Category not found'
            }
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function showIncome (user){
    if(user){
        const query = `SELECT * FROM "Pendapatan" NATURAL JOIN "Kategori_Pendapatan" WHERE "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount){
            return {
                message: 'Income Found',
                showIncome : result.rows
            }
        }else{
            return{
                message: 'No Income Found' 
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function showExpense (user){
    if(user){
        const query = `SELECT * FROM "Pengeluaran" NATURAL JOIN "Kategori_Pengeluaran" WHERE "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount){
            return {
                message: 'Expense Found',
                showExpense : result.rows
            }
        }else{
            return{
                message: 'No Expense Found'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function updateIncome (mm, user){
    const {Pendapatan_Id, Deskripsi, Jumlah, Tanggal, KatPend_Id} = mm;
    if(user){
        const query = `UPDATE "Pendapatan" SET "Deskripsi" = '${Deskripsi}', "Jumlah" = ${Jumlah}, "Tanggal" = '${Tanggal}', "KatPend_Id" = ${KatPend_Id} WHERE "Pendapatan_Id" = ${Pendapatan_Id} AND "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: 'Income Updated'
            }
        }else{
            return{
                message: 'No Income Updated'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function updateExpense (mm, user){
    const {Pengeluaran_Id, Deskripsi, Jumlah, Tanggal, KatPeng_Id} = mm;
    if(user){
        const query = `UPDATE "Pengeluaran" SET "Deskripsi" = '${Deskripsi}', "Jumlah" = ${Jumlah}, "Tanggal" = '${Tanggal}', "KatPeng_Id" = ${KatPeng_Id} WHERE "Pengeluaran_Id" = ${Pengeluaran_Id} AND "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: 'Expense Updated'
            }
        }else{
            return{
                message: 'No Expense Updated'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function deleteIncome (mm, user){
    const {Pendapatan_Id} = mm;
    if(user){
        const query = `DELETE FROM "Pendapatan" WHERE "Pendapatan_Id" = ${Pendapatan_Id} AND "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `Income Deleted`
            }
        }else{
            return{
                message: 'No Income Deleted'
            }
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function deleteExpense (mm, user){
    const {Pengeluaran_Id} = mm;
    if(user){
        const query = `DELETE FROM "Pengeluaran" WHERE "Pengeluaran_Id" = ${Pengeluaran_Id} AND "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `Expense Deleted`
            }
        }else{
            return{
                message: 'No Expense Deleted'
            }
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function updateCategoryIncome (mm, user){
    const {KatPend_Id, Jenis} = mm;
    if(user){
        const query = `UPDATE "Kategori_Pendapatan" SET "Jenis" = '${Jenis}' WHERE "KatPend_Id" = ${KatPend_Id} AND "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `Income Updated`
            }
        }else{
            return{
                message: 'No Income Category Updated'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
    }
}

async function updateCategoryExpense (mm, user){
    const {KatPeng_Id, Jenis} = mm;
    if(user){
        const query = `UPDATE "Kategori_Pengeluaran" SET "Jenis" = '${Jenis}' WHERE "KatPeng_Id" = ${KatPeng_Id} AND "User_Id" = ${user.User_Id}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `Expense Updated`
            }
        }else{
            return{
                message: 'No Expense Category Updated'
            } 
        }
    }
    else{
        return {
            message: 'User not logged in'
        }
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
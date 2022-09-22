<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/



Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});


Route::post('register', 'App\Http\Controllers\UserController@register');
Route::get('view_vio_type', 'App\Http\Controllers\UserController@view_vio_type');

Route::post('addviolation', 'App\Http\Controllers\UserController@addviolation');
Route::get('view_violation', 'App\Http\Controllers\UserController@view_violation');
Route::get('view_car', 'App\Http\Controllers\UserController@view_car');
Route::post('addviolationtype', 'App\Http\Controllers\UserController@addviolationtype');
Route::get('search_name', 'App\Http\Controllers\UserController@search_name');
Route::get('number', 'App\Http\Controllers\UserController@number');
Route::put('/update/{id}', 'App\Http\Controllers\UserController@update'); 
Route::put('/update_car/{id}', 'App\Http\Controllers\UserController@update_car');
Route::put('/update_vio/{id}', 'App\Http\Controllers\UserController@vio_car');
Route::get('delete_car/{id}', 'App\Http\Controllers\UserController@delete_car');
Route::get('delete{id}', 'App\Http\Controllers\UserController@delete');
Route::get('delete_vio/{id}', 'App\Http\Controllers\UserController@delete_vio');
Route::post('login', 'App\Http\Controllers\UserController@login');
Route::get('showalluser', 'App\Http\Controllers\UserController@showalluser'); 
Route::group(['middleware' => 'auth:api'], function(){
Route::get('details', 'App\Http\Controllers\UserController@details');  


Route::get('delete/{id}', 'App\Http\Controllers\UserController@delete');



});
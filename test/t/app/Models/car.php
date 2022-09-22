<?php

namespace App\Models;
use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class car extends Model
{
    use HasApiTokens, Notifiable;
  /**
  * The attributes that are mass assignable.
  *
  * @var array
  */
  protected $fillable = [
  'car_name','car_num'
  ];
  
  }
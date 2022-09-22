<?php

namespace App\Models;
use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class violation extends Model
{
    use HasApiTokens, Notifiable;
  /**
  * The attributes that are mass assignable.
  *
  * @var array
  */
  protected $fillable = [
  'name_vio','price','num_car','nam_car','location','name_of_driver'
  ];
  
  }
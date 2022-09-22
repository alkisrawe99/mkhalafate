<?php

namespace App\Models;
use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class violation_type extends Model
{
    use HasApiTokens, Notifiable;
    public $table = "violation_type";
    protected $fillable = [
        'name','price'
        ];
}

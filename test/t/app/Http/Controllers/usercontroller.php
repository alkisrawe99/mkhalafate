<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\Auth; 
use App\Models\car; 
use Validator;
use Illuminate\Support\Facades\Input;
use Illuminate\Http\Request;
use App\Models\User; 
use App\Models\violation; 
use App\Models\violation_type; 
 


class UserController extends Controller
{
public $successStatus = 200;
/** 
     * login api 
     * 
     * @return \Illuminate\Http\Response 
     */ 
    public function login(){ 
        if(Auth::attempt(['email' => request('email'), 'password' => request('password')])){ 
            $user = Auth::user(); 
            $success['token'] =  $user->createToken('MyApp')-> accessToken; 
            $success['car_num'] =  $user->car_num;
            $success['status'] =  $user->status;
            return response()->json(['success' => $success], $this-> successStatus); 
        } 
        else{ 
            return response()->json(['error'=>'Unauthorised'], 401); 
        } 
    }
/** 
 * 
 * 
     * Register api 
     * 
     * @return \Illuminate\Http\Response 
     */ 
    public function register(Request $request) 
    { 
        $validator = Validator::make($request->all(), [ 
            'name' => 'required', 
            'car_name' => 'required',
            'car_num' => 'required',
            'email' => 'required|email', 
            'password' => 'required', 
            'c_password' => 'required|same:password',
        ]);
if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
$input = $request->all(); 
$i = $request->all(); 
        $input['password'] = bcrypt($input['password']); 
        $car = car::create($i);
        //$carss = car::create($request->car_num);
        $user = User::create($input); 
       
        $success['token'] =  $user->createToken('MyApp')-> accessToken; 
        $success['name'] =  $user->name;
        $success['car_name'] =  $user->car_name;
        $success['car_num'] =  $user->car_num;
        $success['car_num'] =  $car->car_num; 
       $success['car_name'] =  $car->car_name;

       
        
return response()->json(['RA'=> true,'data'=>$success], $this-> successStatus); 
    }
/** login 
     * details api 
     * 
     * @return \Illuminate\Http\Response 
     */ 
    public function details() 
    { 
        $user = Auth::user(); 
        return response()->json(['success' => $user], $this-> successStatus); 
    } 



    public function delete($id) 
    { 
       
        $success=user::find($id);
        $result=$success->delete();
        if( $result){
      
       return response()->json(['message'=>'user delete sucess']);
        }
        else{

            return response()->json(['message'=>'error not found'],200);
        }
        
    }



    public function delete_vio($id) 
    { 
       
        $success=violation::find($id);
        $result=$success->delete();
        if( $result){
      
       return response()->json(['message'=>'violation delete sucess']);
        }
        else{

            return response()->json(['message'=>'error not found'],200);
        }
        
    }


    public function delete_car($id) 
    { 
       
        $success=car::find($id);
        $result=$success->delete();
        if( $result){
      
       return response()->json(['message'=>'car delete sucess'],200);
        }
        else{

            return response()->json(['message'=>'error not found'],200);
        }
        
    }



    public function showalluser(){
$data=User::all();
return response()->json(['User' => $data], $this-> successStatus); 

    }

    public function view_violation(){
        $data=violation::all();
        
        return response()->json(['violation' => $data], $this-> successStatus); 
        
            }
            



            
    public function view_car(){
        $data=car::all();
        
        return response()->json(['car' => $data], $this-> successStatus); 
        
            }

            public function view_vio_type(){
                $data=violation_type::all();
                
                return response()->json(['vio' => $data], $this-> successStatus); 
                
                    }


    public function addviolation(Request $request) 
    { 
        $validator = Validator::make($request->all(), [ 
            'name_vio' => 'required', 
            'price' => 'required',
            'num_car' => 'required',
            'nam_car' => 'required', 
            'location' => 'required', 
            'name_of_driver' => 'required',         
        ]);
if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
$input = $request->all(); 

        $violation = violation::create($input); 
    
        $success['name_vio'] =  $violation->name_vio;
        $success['price'] =  $violation->price;
        $success['num_car'] =  $violation->num_car;
        $success['nam_car'] =  $violation->nam_car; 
       $success['location'] =  $violation->location;
       $success['name_of_driver'] =  $violation->name_of_driver;
      // $success['data'] =  $violation->data;
        
return response()->json(['RA'=> true,'success'=>$success], $this-> successStatus); 
    }







    


    public function update(Request $request, $id)
    {
        try {
    
            $patientData = $request->all();
            $patient = user::find($id);
            if($patient == null){
                $return = ['data' => ['msg' => 'Could not find user']];
                return response()->json($return, 404);
            }
            $patient->update($patientData);
            $return = ['data' => ['msg' => 'user updated successfully!']];
            return response()->json($return, 201);
            
    
    
        } catch (Exception $e) {
            if(config('app.debug')) {
                return response()->json(ApiError::errorMessage($e->getMessage(), 1011),  500);
            }
            return response()->json(ApiError::errorMessage('There was an error performing the update operation', 1011), 500);
        }
    }


    public function update_car(Request $request, $id)
    {
        try {
    
            $cardata = $request->all();
            $car = car::find($id);
            if($car == null){
                $return = ['data' => ['msg' => 'Could not find car']];
                return response()->json($return, 404);
            }
            $car->update($cardata);
            $return = ['data' => ['msg' => 'car updated successfully!']];
            return response()->json($return, 201);
            
    
    
        } catch (Exception $e) {
            if(config('app.debug')) {
                return response()->json(ApiError::errorMessage($e->getMessage(), 1011),  500);
            }
            return response()->json(ApiError::errorMessage('There was an error performing the update operation', 1011), 500);
        }
    }

    public function addviolationtype(Request $request) 
    { 
        $validator = Validator::make($request->all(), [ 
            'name' => 'required', 
            'price' => 'required',
        ]);
if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
$input = $request->all(); 

        $type = violation_type::create($input); 
    
        $success['name'] =  $type->name;
        $success['price'] =  $type->price;
       
return response()->json(['data'=>$success], $this-> successStatus); 
    }






    public function search_name(Request $request)
{
    $data = $request->get('data');
    $member_info = violation::where('name_of_driver', 'like', "%{$data}%")->orWhere( 'num_car', 'like', "%{$data}%")
             ->get();

    return Response()->json([ 'data' => $member_info], 200);
}
public function number(Request $request)
{
    $data = $request->get('data');
    $member_info = violation::Where( 'num_car', $data)
             ->get();

    return Response()->json([ 'data' => $member_info], 200);
}



public function vio_car(Request $request, $id)
    {
        try {
    
            $cardata = $request->all();
            $vio = violation::find($id);
            if($vio == null){
                $return = ['data' => ['msg' => 'Could not find vio']];
                return response()->json($return, 404);
            }
            $vio->update($cardata);
            $return = ['data' => ['msg' => 'carrrr updated successfully!']];
            return response()->json($return, 201);
            
    
    
        } catch (Exception $e) {
            if(config('app.debug')) {
                return response()->json(ApiError::errorMessage($e->getMessage(), 1011),  500);
            }
            return response()->json(ApiError::errorMessage('There was an error performing the update operation', 1011), 500);
        }
    }



}
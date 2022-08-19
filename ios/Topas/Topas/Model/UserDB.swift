//
//  UserDB.swift
//  Topas
//
//  Created by 김경호 on 2022/07/26.
//
import Foundation
import FirebaseFirestore

public class UserDB{
    static func isFirstTime() -> Bool{
        let defaults = UserDefaults.standard
        if defaults.object(forKey: "isFirstTime") == nil{
            defaults.set("No", forKey: "isFirstTime")
            return true
        } else{
            return false
        }
    }
    
    static func isLogin() -> Bool{
        let defaults = UserDefaults.standard
        if defaults.object(forKey: "isLogin") == nil{
            return false
        } else {
            return true
        }
    }
    
    static func LoginSuccess(){
        let defaults = UserDefaults.standard
        defaults.set(true, forKey: "isLogin")
    }
    
    
    static func signupModel(email : String, name : String, nick_name : String, photo : String){
        let db = Firestore.firestore()
        
        db.collection("user").addDocument(data: [:]) { error in
            if let error = error{
                print(error)
            }
            else
            {
                print("DB Success")
                
            }
        }
    }
    
    static func setDefaultImage(){
        UserDefaults.standard.set("defaultProfile", forKey: "profile_photo_uri")
    }
    
    static func setProfileImage(image: UIImage){
        
    }
    
    func setinit(_ email: String, _ name: String, _ nick_name: String, _ profile_photo_uri: String){
        let defaults = UserDefaults.standard
        
        defaults.set(email, forKey: "email")
        defaults.set(name, forKey: "name")
        defaults.set(nick_name, forKey: "nick_name")
        defaults.set(true, forKey: "exposure")
        defaults.set([], forKey: "skill")
        defaults.set("", forKey: "introduce")
        defaults.set(profile_photo_uri, forKey: "profile_photo_uri")
    }
}

struct User{
    var email : String
    var name : String
    var nick_name : String
    var exposure : Bool
    var skill : [String]
    var introduce : String
    var profile_photo_uri : String
    
    init(){
        self.email = ""
        self.name = ""
        self.nick_name = ""
        self.skill = []
        self.exposure = true
        self.introduce = ""
        self.profile_photo_uri = "defaultProfile"
    }
}

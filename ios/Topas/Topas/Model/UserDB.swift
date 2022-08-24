//
//  UserDB.swift
//  Topas
//
//  Created by 김경호 on 2022/07/26.
//
import Foundation
import FirebaseFirestore
import FirebaseStorage

public class UserDB{
    static func isFirstTime() -> Bool{
        let defaults = UserDefaults.standard
        if defaults.object(forKey: "isFirstTime") == nil{
            defaults.set(true, forKey: "isFirstTime")
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
        defaults.set(true, forKey: "isFirstTime")
        defaults.set(true, forKey: "isLogin")
    }
    
    
    static func signupModel(email : String, name : String, nick_name : String, photo : UIImage?){
        let db = Firestore.firestore()
        
        //UserDefaults에 기본 데이터들을 저장
        self.setinit(email, name, nick_name)
        
        //Firestore에 유저 프로필 정보 생성 및 저장
        let profileDocument = db.collection("user").document() // Document 생성
        
        UserDefaults.standard.set(profileDocument.documentID, forKey: "ProfileID")
        
        let date = DateFormatter()
        date.dateFormat = "yyyy년 MM월 dd일 a hh시 mm분"
        date.locale = Locale(identifier: "ko_KR")
        
        profileDocument.setData(["email":email,
                                 "exposure":true,
                                 "introduce":"",
                                 "name":name,
                                 "nick_name":nick_name,
                                 "skill":[],
                                 "update_at" : date.string(from: Date())
                                ])
        
        //프로필 사진을 로컬 및 Storage에 저장, UserDefaults값을 profile로바꿔줌
        if photo != nil{
            ImageFileManager.saveImage(name: "profile.jpg", image: photo!)
            FirebaseStorageManager.uploadImage(image: photo!) { url in
                if let url = url {
                    UserDefaults.standard.set(url.absoluteString, forKey: "photoURL")
                    profileDocument.setData(["profile_photo_uri":url.absoluteString])
                }
            }
        }
        LoginSuccess()
    }
    
    static func setDefaultImage(){
        UserDefaults.standard.set("defaultProfile", forKey: "profile_photo")
    }
    
    static func setProfileImage(){
        UserDefaults.standard.set("Profile", forKey: "profile_photo")
    }
    
    static func isDefaultProfile() -> Bool{
        if UserDefaults.standard.string(forKey: "profile_photo") == "defaultProfile"{
            return true
        } else {
            return false
        }
    }
    
    static func setinit(_ email: String, _ name: String, _ nick_name: String){
        let defaults = UserDefaults.standard
        
        defaults.set(email, forKey: "email")
        defaults.set(name, forKey: "name")
        defaults.set(nick_name, forKey: "nick_name")
        defaults.set(true, forKey: "exposure")
        defaults.set([], forKey: "skill")
        defaults.set("", forKey: "introduce")
    }
    
    func inputDB(_ email: String? = nil,_ name: String? = nil, _ nick_name : String? = nil, _ exposure : Bool? = nil, _ introduce : String? = nil){
        if email != nil{
            
        }
        
        if name != nil{
            
        }
        
        if nick_name != nil{
            
        }
        
        if exposure != nil{
            
        }
        
        if introduce != nil{
            
        }
    }
}

struct User{
    var email : String
    var name : String
    var nick_name : String
    var exposure : Bool
    var skill : [String]
    var introduce : String
    var profile_photo : String
    
    init(){
        self.email = ""
        self.name = ""
        self.nick_name = ""
        self.skill = []
        self.exposure = true
        self.introduce = ""
        self.profile_photo = "defaultProfile"
    }
}

class ImageFileManager {
    static let shared: ImageFileManager = ImageFileManager()
    
    static func saveImage(name: String, image: UIImage) -> Bool{
        guard let data = image.jpegData(compressionQuality: 1) ?? image.pngData() else {return false}
        guard let directory = try? FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false) as NSURL else {return false}
        do {
            try data.write(to: directory.appendingPathComponent(name)!)
            return true
        } catch {
            print(error.localizedDescription)
            return false
        }
    }
    
    static func getImage(name: String) -> UIImage? {
        if let dir = try? FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false){
            return UIImage(contentsOfFile: URL(fileURLWithPath: dir.absoluteString).appendingPathComponent(name).path)
        }
        return nil
    }
}

class FirebaseStorageManager{
    static func uploadImage(image: UIImage, completion: @escaping (URL?) -> Void){
        guard let imageData = image.jpegData(compressionQuality: 1) else {return}
        let metaData = StorageMetadata()
        metaData.contentType = "image/jpeg"
        
        let imageName = "profile_photo_" + UserDefaults.standard.string(forKey: "email")!
        
        let firebaseReference = Storage.storage().reference().child(imageName).putData(imageData, metadata: metaData){ (metaData, error) in
            if let error = error{
                print(error.localizedDescription)
                return
            } else {
                print("Input Succcess")
            }
        }
    }
}

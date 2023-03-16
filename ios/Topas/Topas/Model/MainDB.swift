//
//  MainDB.swift
//  Topas
//
//  Created by 김경호 on 2022/12/28.
//
//  팀원 모집 리스트 관련한 데이터 보관

import Foundation
import FirebaseFirestore

public class MainDB{
    
    
    public static var userlist : [MainCellModel] = []
    public static var teamlist : [MainTeamModel] = []
    
    
    // 유저 목록 가져오기
    public static func LoadList() -> Void{
        
        let db = Firestore.firestore()
        
        db.collection("user").getDocuments() { (querySnapshot, err) in
            if let err = err {
                print("Error getting documents: \(err)")
            } else {
                for document in querySnapshot!.documents {
                    // 읽어온 데이터 Cell에 맞게 가공
                    let data = MainCellModel(profile: UIImage(named: "defaultProfile")!, introduce: document.data()["introduce"]! as! String, nickname: document.data()["nick_name"]! as! String, skills: document.data()["skill"]! as! [String])
                    
                    // 데이터베이스에서 가져온 유저 데이터 삽입
                    self.userlist.append(data)
                }
                
            }
            print("User List 값 로딩")
            print(self.userlist)
        }
        
        db.collection("team").getDocuments{ (querySnapshot, err) in
            if let err = err{
                print("Error Getting Documents: \(err)")
            } else {
                for document in querySnapshot!.documents{
                    // 읽어온 데이터 Cell에 맞게 제공
                    let data = MainTeamModel(explanation: document.data()["explanation"]! as? String, leader_document_id: document.data()["leader_document_id"]! as? String, leader_nick_name: document.data()["leader_nick_name"]! as? String, title: document.data()["title"]! as? String, skills: document.data()["skill"] as! [String])
                    
                    // 데이터베이스에서 가져온 팀 데이터 삽입
                    self.teamlist.append(data)
                }
            }
            
            print("Team List 값 로딩")
            print(self.teamlist)
        }
        
//        db.collection("team").getDocuments() { (querySnapshot, err) in
//            if let err = err {
//                print("Error getting documents: \(err)")
//            } else {
//                for document in querySnapshot!.documents {
//                    // 읽어온 데이터 Cell에 맞게 가공
////                    let data = MainCellModel(profile: UIImage(named: "defaultProfile")!, introduce: document.data()["introduce"]! as! String, nickname: document.data()["nick_name"]! as! String, skills: document.data()["skill"]! as! [String])
//                    let data = MainTeamModel(explanation: document.data()["explanation"], leader_document_id: document.data()["leader_document_id"], leader_nick_name: document.data()["leader_nick_name"], title: document.data()["title"], skills: document.data()["skill"])
//                    // 데이터베이스에서 가져온 유저 데이터 삽입
//                    self.teamlist.append(data)
//                }
//            }
//            print("Main List 값 로딩")
//            print(self.teamlist)
//        }
    }
    
    public static func getUserList() -> [MainCellModel]{
        return self.userlist
    }
    
    public static func getTeamList() -> [MainTeamModel]{
        return self.teamlist
    }
    
    // 해당 유저의 프로필 사진을 가져다 줌
    public static func getProfile(userID : String) -> Void{
        let db = Firestore.firestore()
        var profile_address : String
        
        db.collection("user").document(userID).getDocument(){(querySnapshot, err) in
            let data = querySnapshot!.data()!["profile_photo_uri"]

            var profile : UIImage

            if let err = err{
                print("getProfile Error")
            } else {
//                print(querySnapshot!.data())
                if data is NSNull{
                    print("nil 값")
                    
                } else {
//                    profile = ImageFileManager.getImage(name: profile_address)
                    print("Profile 값 가지고 있음")
                    print(querySnapshot!.data()!["name"])
                    print(type(of: querySnapshot!.data()!["profile_photo_uri"]))
                    print(data!)

                }
                
            }

        }
        
//        let db = Firestore.firestore().collection("user").document(userID).getDocument(){ (querySnapshot, err) in
//            let data = querySnapshot!.data()!
//
//        }
        
//        let profile = ImageFileManager.getImage(name: profile_address)
//        return profile
    }
    

}

public struct MainCellModel{
    let profile : UIImage?
    let introduce : String
    let nickname : String
    let skills : [String]
}

public struct MainUserModel : Codable{ //test
    let introduce : String
    let nick_name : String
    let name : String
    let profile_photo_uri : String
    let email : String
    let skills : [String]
}

public struct MainTeamModel{
    let explanation : String?
    let leader_document_id : String?
    let leader_nick_name : String?
    let title : String?
    let skills : [String]
    
}

struct Sing: Codable {
    let title: String
    let singer: String
}

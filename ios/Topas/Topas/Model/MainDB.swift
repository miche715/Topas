//
//  MainDB.swift
//  Topas
//
//  Created by 김경호 on 2022/12/28.
//

import Foundation
import FirebaseFirestore

public class MainDB{
    
    
    public static var userlist : [MainCellModel] = []
        
    public static func LoadList() -> Void{
        
        let db = Firestore.firestore()
        
        db.collection("user").getDocuments() { (querySnapshot, err) in
            if let err = err {
                print("Error getting documents: \(err)")
            } else {
            
                for document in querySnapshot!.documents {
                    
                    let data = MainCellModel(profile: UIImage(named: "defaultProfile")!, introduce: document.data()["introduce"]! as! String, nickname: document.data()["nick_name"]! as! String, skills: document.data()["skill"]! as! [String])
                    
                    self.userlist.append(data)

                    
                }
                
            }
            print("Main List 값 로딩")
            print(self.userlist)
        }
    }
    
    public static func getUserList() -> [MainCellModel]{
        return self.userlist
    }
}

public struct MainCellModel{
    let profile : UIImage
    let introduce : String
    let nickname : String
    let skills : [String]
}


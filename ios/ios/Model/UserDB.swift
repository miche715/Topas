//
//  UserDB.swift
//  ios
//
//  Created by 김경호 on 2022/07/13.
//

import Foundation

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
}

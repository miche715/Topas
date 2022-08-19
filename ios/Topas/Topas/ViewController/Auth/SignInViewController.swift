//
//  SignInViewController.swift
//  Topas
//
//  Created by 김경호 on 2022/08/01.
//

import UIKit
import FirebaseAuth

class SingIn : UIViewController{
    let vc = SignInView()
    
    override func viewDidLoad() {
        self.view = vc
        
        vc.idbox.delegate = self
        vc.pwbox.delegate = self
        
        vc.signupButton.addTarget(self, action: #selector(signUpLink), for: .touchUpInside)
        vc.SignInButton.addTarget(self, action: #selector(signIn), for: .touchUpInside)
    }
    
    @objc func signUpLink(){
        self.performSegue(withIdentifier: "toSignUp", sender: self)
    }
    
    @objc func signIn(){
        let id = vc.idbox.text!
        let pw = vc.pwbox.text!
        if id.count > 0 && pw.count > 0 {
            Auth.auth().signIn(withEmail: id, password: pw){ user, error in
                if user != nil{
                    print("Login Success")
                    UserDB.LoginSuccess()
                    guard let mainViewController = self.storyboard?.instantiateViewController(withIdentifier: "Main") as? ViewController else {return}
                    mainViewController.modalTransitionStyle = .coverVertical
                    mainViewController.modalPresentationStyle = .fullScreen
                    self.present(mainViewController, animated: true, completion: nil)
                }
                else
                {
                    self.vc.loginFlagLabel.text = "계정 정보가 잘못되었습니다."
                    print("Login Fail")
                }
            }
        }
        else if id.count <= 0 && pw.count > 0{
            vc.loginFlagLabel.text = "아이디를 입력해주세요."
        }
        else if id.count > 0 && pw.count <= 0{
            vc.loginFlagLabel.text = "비밀번호를 입력해주세요."
        }
        else{
            vc.loginFlagLabel.text = "아이디와 비밀번호를 입력해주세요."
        }
        
    }
}
extension SingIn : UITextFieldDelegate{
    //Text Field에서 Enter 눌렀을 때 포커싱 옮기기
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField{
        case vc.idbox:
            // 이메일 -> 비밀번호
            vc.pwbox.becomeFirstResponder()
        default:
            // 닉네임 -> 회원가입
            vc.SignInButton.becomeFirstResponder()
        }
        return false
    }
    
    // 키보드에서 사용자가 키 하나를 눌렀을 때 취해야 할 동작을 지정
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        
        let utf8Char = string.cString(using: .utf8)
        let isBackSpace = strcmp(utf8Char, "\\b")
        
        switch textField{
        case vc.idbox:
            if string.idCheck() || isBackSpace == -92 {
                return true
            }
            return false
        default:
            if string.pwcheck() || isBackSpace == -92 {
                return true
            }
            return false
        }
        return true
    }
}
extension String{
    // 필터링을 위함
    func idCheck() -> Bool {
        do{
            let regex = try NSRegularExpression(pattern: "^[a-zA-Z0-9@.]$", options: .caseInsensitive)
            if let _ = regex.firstMatch(in: self,options: NSRegularExpression.MatchingOptions.reportProgress, range: NSMakeRange(0, self.count)){
                return true
            }
        }catch{
            return false
        }
        return false
    }
    
    
    func pwcheck() -> Bool {
        do {
            let regex = try NSRegularExpression(pattern: "^[a-zA-Z0-9@#$%^&*.!?]$", options: .caseInsensitive)
            if let _ = regex.firstMatch(in: self, options: NSRegularExpression.MatchingOptions.reportProgress, range: NSMakeRange(0, self.count)){
                return true
            }
        } catch {
            return false
        }
        return false
    }
}

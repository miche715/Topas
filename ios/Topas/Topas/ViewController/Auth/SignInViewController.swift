//
//  SignInViewController.swift
//  Topas
//
//  Created by 김경호 on 2022/08/01.
//

import UIKit

class SingIn : UIViewController{
    let vc = SignInView()
    
    override func viewDidLoad() {
        vc.signupButton.addTarget(self, action: #selector(signUpLink), for: .touchUpInside)
        vc.SignInButton.addTarget(self, action: #selector(signIn), for: .touchUpInside)
    }
    
    override func loadView() {
        
        
        
        self.view = vc
    }
        
    
    @objc func signUpLink(){
        self.performSegue(withIdentifier: "toSignUp", sender: self)
    }
    
    @objc func signIn(){
        let id = vc.idbox.text!
        let pw = vc.pwbox.text!
    }
}

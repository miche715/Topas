//
//  OnBoardView.swift
//  Topas
//
//  Created by 김경호 on 2022/07/25.
//

import UIKit

class onboard1: UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    let vc = UIView()
    
    let title: UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "팀을 찾고 계십니까?"
        label.frame = CGRect(x: 30, y: 120, width: 250, height: 50)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        label.textColor = UIColor.white
        return label
    }()
    
    
    let des1 : UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Topas에는"
        label.frame = CGRect(x: 30, y: 180, width: 150, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des2 : UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "수 많은 팀들이"
        label.frame = CGRect(x: 30, y: 210, width: 150, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des3 : UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "당신을 위해 기다립니다."
        label.frame = CGRect(x: 30, y: 240, width: 300, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    private func commonInit(){
        self.addSubview(title)
        self.addSubview(des1)
        self.addSubview(des2)
        self.addSubview(des3)
    }
}


class onboard2: UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    let vc = UIView()
    
    let title: UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "팀원을 찾고 계십니까?"
        label.frame = CGRect(x: 30, y: 120, width: 300, height: 50)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        label.textColor = UIColor.white
        return label
    }()
    
    let des1 : UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Topas에는"
        label.frame = CGRect(x: 30, y: 180, width: 200, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des2 : UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "수 많은 사람들이"
        label.frame = CGRect(x: 30, y: 210, width: 200, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des3 : UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "당신을 위해 기다립니다."
        label.frame = CGRect(x: 30, y: 240, width: 300, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    private func commonInit(){
        self.addSubview(title)
        self.addSubview(des1)
        self.addSubview(des2)
        self.addSubview(des3)
    }
}

class onboard3: UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    let vc = UIView()
    
    let title: UILabel = {
        let label = UILabel()
        //label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Topas 지금 시작하세요."
        label.frame = CGRect(x: 30, y: 120, width: 300, height: 50)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        label.textColor = UIColor.white
        return label
    }()
    
    
    let signupButton: UIButton = {
        let button = UIButton()
        //button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("새로운 회원으로 가입하기", for: .normal)
        button.setTitleColor(UIColor.black, for: .normal)
        button.frame = CGRect(x: 40, y: 650, width: 350, height: 40)
        button.backgroundColor = .white
        button.setTitleColor(#colorLiteral(red: 0.002839220921, green: 0.4000240564, blue: 1, alpha: 1), for: .normal)
        //그림자
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.masksToBounds = false
        button.layer.shadowOffset = CGSize(width: 0, height: 4)
        button.layer.shadowOpacity = 0.3
        return button
    }()
    
    let signinButton: UIButton = {
        let button = UIButton()
        //button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("기존 회원으로 시작하기", for: .normal)
        button.setTitleColor(#colorLiteral(red: 0.002839220921, green: 0.4000240564, blue: 1, alpha: 1), for: .normal)
        button.frame = CGRect(x: 40, y: 700, width: 350, height: 40)
        button.backgroundColor = .white
        button.setTitleColor(#colorLiteral(red: 0.002839220921, green: 0.4000240564, blue: 1, alpha: 1), for: .normal)
        //그림자
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.masksToBounds = false
        button.layer.shadowOffset = CGSize(width: 0, height: 4)
        button.layer.shadowOpacity = 0.3
        return button
    }()
    

    
    
    func singinLink(){
        let storyboard = UIStoryboard(name: "SignIn", bundle: nil)
        let signin = storyboard.instantiateViewController(identifier: "SignInView")
        
        //self.present(signin, animated: true)
    }
    
    
    private func commonInit(){
        
        self.addSubview(title)
        self.addSubview(signinButton)
        self.addSubview(signupButton)
    }
}

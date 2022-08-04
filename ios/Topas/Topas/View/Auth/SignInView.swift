//
//  SignIn.swift
//  Topas
//
//  Created by 김경호 on 2022/07/25.
//
import UIKit

class SignInView : UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    
    
    let title: UILabel = {
        let label = UILabel()
        label.numberOfLines = 3
        label.text = "Topas 에서\n스터디, 프로젝트\n팀 완성 하세요."
        label.frame = CGRect(x: 30, y: 100, width: 250, height: 150)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        
        return label
    }()
    
    
    let idbox: UITextField = {
        let idbox = UITextField()
        idbox.placeholder = "이메일을 입력해주세요."
        idbox.textColor = .gray
        idbox.backgroundColor = .systemGray6
        idbox.frame = CGRect(x: 30, y: 300, width: 350, height: 50)
        idbox.layer.cornerRadius = 1
        
        return idbox
    }()
    
    let pwbox: UITextField = {
        let pwbox = UITextField()
        
        pwbox.placeholder = "비밀번호를 입력해주세요."
        pwbox.textColor = .gray
        pwbox.backgroundColor = .systemGray6
        pwbox.frame = CGRect(x: 30, y: 360, width: 350, height: 50)
        pwbox.layer.cornerRadius = 1
        
        return pwbox
    }()
    
    let SignInButton: UIButton = {
        let button = UIButton()
        
        button.setTitle("로그인", for: .normal)
        button.setTitleColor(UIColor.white, for: .normal)
        button.frame = CGRect(x: 30, y: 440, width: 350, height: 40)
        button.backgroundColor = #colorLiteral(red: 0.002839220921, green: 0.4000240564, blue: 1, alpha: 1)
        //그림자
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.masksToBounds = false
        button.layer.shadowOffset = CGSize(width: 0, height: 4)
        button.layer.shadowOpacity = 0.3
        
        button.layer.cornerRadius = 6
        
        button.layer.borderWidth = 1
        
        button.layer.borderColor = UIColor.black.cgColor
        
        return button
    }()
    
    let underText : UILabel = {
        let label = UILabel()
        
        label.text = "계정을 잊으셨습니까?\t\t\t 또는\n아직 회원이 아니십니까?"
        label.font = UIFont.systemFont(ofSize: 14)
        label.numberOfLines = 2
        label.textColor = .lightGray
        label.textAlignment = .center
        return label
    }()
    
    let findEmailButton : UIButton = {
        let button = UIButton()
        
        button.setTitle("이메일 찾기", for: .normal)
        button.setTitleColor(UIColor.blue, for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 15)
        
        return button
    }()
    
    let findPWButton : UIButton = {
        let button = UIButton()
        
        button.setTitle("비밀번호 찾기", for: .normal)
        button.setTitleColor(UIColor.blue, for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 15)
        
        return button
    }()
    
    let signupButton : UIButton = {
        let button = UIButton()
        
        button.setTitle("회원가입", for: .normal)
        button.setTitleColor(UIColor.blue, for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 15)
        
        return button
    }()
    
    private func commonInit(){
        self.backgroundColor = .white
        
        self.addSubview(title)
        self.title.translatesAutoresizingMaskIntoConstraints = false
        self.title.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 50).isActive = true
        self.title.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 30).isActive = true
        
        self.addSubview(idbox)
        
        self.addSubview(pwbox)
        self.addSubview(SignInButton)
        
        
        self.addSubview(underText)
        self.underText.translatesAutoresizingMaskIntoConstraints = false
        self.underText.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 410).isActive = true
        self.underText.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 45).isActive = true
        
        self.addSubview(findEmailButton)
        self.findEmailButton.translatesAutoresizingMaskIntoConstraints = false
        self.findEmailButton.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 402).isActive = true
        self.findEmailButton.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 170).isActive = true
        
        self.addSubview(findPWButton)
        self.findPWButton.translatesAutoresizingMaskIntoConstraints = false
        self.findPWButton.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 402).isActive = true
        self.findPWButton.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 275).isActive = true
        
        self.addSubview(signupButton)
        self.signupButton.translatesAutoresizingMaskIntoConstraints = false
        self.signupButton.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 420).isActive = true
        self.signupButton.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 230).isActive = true
    }
    
    
}

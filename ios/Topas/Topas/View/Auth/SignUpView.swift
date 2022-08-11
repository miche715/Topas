//
//  SignUpView.swift
//  Topas
//
//  Created by 김경호 on 2022/08/01.
//

import UIKit

class SignUpView: UIView{
    let scrollView = UIScrollView().then{
        $0.backgroundColor = .white
        $0.frame = CGRect(x: 50, y: 50, width: 100, height: 100)
        
        $0.translatesAutoresizingMaskIntoConstraints = false
    }
    
    let profileImage = UIImageView().then{
        $0.frame = CGRect(x: 100, y: 100, width: 100, height: 100)
        //$0.layer.cornerRadius = $0.frame.width / 2
        $0.clipsToBounds = true
        $0.backgroundColor = .blue
        $0.translatesAutoresizingMaskIntoConstraints = false
        $0.contentMode = .scaleAspectFill
    }
    
    let profileLabel = UILabel().then{
        $0.text = "프로필 사진"
        $0.textColor = .blue
        $0.frame = CGRect(x: 5, y: 5, width: 100, height: 100)
        
        $0.translatesAutoresizingMaskIntoConstraints = false
    }
    
    let emailLabel = UILabel().then{
        $0.text = "* 이메일"
    }
    
    let emailInput : UITextField = {
        let view = UITextField()
        
        view.placeholder = "example@domain.com 형식의 이메일을 입력하세요."
        view.textColor = .gray
        view.autocapitalizationType = .none
        
        let underline = UIView().then{
            $0.backgroundColor = .black
        }
        
        view.addSubview(underline)
        underline.snp.makeConstraints{
            $0.width.equalTo(view.snp.width)
            $0.bottom.equalTo(view.snp.bottom)
            $0.height.equalTo(1)
        }
        
        return view
    }()
    
    let emailckLabel = UILabel()
    
    let pwLabel = UILabel().then{
        $0.text = "* 패스워드"
    }
    
    let pwInput : UITextField = {
        let view = UITextField()
        
        view.placeholder = "4~20 자리 영어 대﹒소문자, 숫자, 특수문자 조합"
        view.textColor = .gray
        //view.isSecureTextEntry = true
        view.textContentType = .password
        view.autocapitalizationType = .none
        
        let underline = UIView().then{
            $0.backgroundColor = .black
        }
        
        view.addSubview(underline)
        underline.snp.makeConstraints{
            $0.width.equalTo(view.snp.width)
            $0.bottom.equalTo(view.snp.bottom)
            $0.height.equalTo(1)
        }
        
        return view
    }()
    
    let pwckLabel = UILabel()
    
    let pwreLabel = UILabel().then{
        $0.text = "* 패스워드 확인"
    }
    
    let pwreInput : UITextField = {
        let view = UITextField()
        
        view.placeholder = "4~20 자리 영어 대﹒소문자, 숫자, 특수문자 조합"
        view.textColor = .gray
        //view.isSecureTextEntry = true
        view.textContentType = .password
        view.autocapitalizationType = .none
        
        let underline = UIView().then{
            $0.backgroundColor = .black
        }
        
        view.addSubview(underline)
        underline.snp.makeConstraints{
            $0.width.equalTo(view.snp.width)
            $0.bottom.equalTo(view.snp.bottom)
            $0.height.equalTo(1)
        }
        return view
    }()
    
    let pwreckLabel = UILabel()
    
    let nameLabel = UILabel().then{
        $0.text = "* 이름"
    }
    
    let nameInput : UITextField = {
        let view = UITextField()
        
        view.placeholder = "한글로 이루어진 이름"
        view.textColor = .gray
        
        let underline = UIView().then{
            $0.backgroundColor = .black
        }
        
        view.addSubview(underline)
        underline.snp.makeConstraints{
            $0.width.equalTo(view.snp.width)
            $0.bottom.equalTo(view.snp.bottom)
            $0.height.equalTo(1)
        }
        
        return view
    }()
    
    let nameckLabel = UILabel()
    
    let nicknameLabel = UILabel().then{
        $0.text = "* 닉네임"
    }
    
    let nicknameInput : UITextField = {
        let view = UITextField()
        
        view.placeholder = "2~8 자리 한글, 영어 대﹒소문자, 숫자 조합"
        view.textColor = .gray
        view.autocapitalizationType = .none
        
        let underline = UIView().then{
            $0.backgroundColor = .black
        }
        
        view.addSubview(underline)
        underline.snp.makeConstraints{
            $0.width.equalTo(view.snp.width)
            $0.bottom.equalTo(view.snp.bottom)
            $0.height.equalTo(1)
        }
        
        return view
    }()
    
    let nicknameckLabel = UILabel()
    
    let SignUpbutton = UIButton().then{
        $0.setTitle("회원가입", for: .normal)
        $0.setTitleColor(UIColor.white, for: .normal)
        $0.backgroundColor = #colorLiteral(red: 0.002839220921, green: 0.4000240564, blue: 1, alpha: 1)
        
        $0.layer.shadowColor = UIColor.black.cgColor
        $0.layer.masksToBounds = false
        $0.layer.shadowOffset = CGSize(width: 0, height: 4)
        $0.layer.shadowOpacity = 0.3
        
        $0.layer.cornerRadius = 6
        $0.layer.borderWidth = 1
        $0.layer.borderColor = UIColor.black.cgColor
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    private func commonInit(){
        self.backgroundColor = .white
        self.addSubview(scrollView)
        
        
        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: self.safeAreaLayoutGuide.bottomAnchor),
            scrollView.widthAnchor.constraint(equalTo: self.safeAreaLayoutGuide.widthAnchor)
        ])
        
        scrollView.addSubview(profileImage)
        profileImage.snp.makeConstraints{ make in
            make.top.equalTo(scrollView.snp.top).offset(100)
            make.centerX.equalTo(scrollView)
            //make.width.equalTo(scrollView).multipliedBy(0.2)
            make.width.height.equalTo(100)
        }
        
        scrollView.addSubview(profileLabel)
        profileLabel.snp.makeConstraints{ make in
            make.top.equalTo(profileImage.snp.bottom).offset(20)
            make.centerX.equalTo(profileImage)
        }
        
        scrollView.addSubview(emailLabel)
        emailLabel.snp.makeConstraints{
            $0.top.equalTo(profileLabel.snp.bottom).offset(50)
            $0.leading.equalTo(scrollView.snp.leading).offset(10)
            
        }
        
        scrollView.addSubview(emailInput)
        emailInput.snp.makeConstraints{ make in
            make.top.equalTo(emailLabel.snp.bottom).offset(10)
            make.width.equalTo(scrollView.snp.width).multipliedBy(0.8)
            make.centerX.equalTo(profileLabel)
        }
        
        scrollView.addSubview(emailckLabel)
        emailckLabel.snp.makeConstraints{
            $0.top.equalTo(emailInput.snp.bottom).offset(2)
            $0.leading.equalTo(emailInput.snp.leading)
        }
        
        scrollView.addSubview(pwLabel)
        pwLabel.snp.makeConstraints{
            $0.top.equalTo(emailInput.snp.bottom).offset(30)
            $0.leading.equalTo(scrollView.snp.leading).offset(10)
        }
        
        scrollView.addSubview(pwInput)
        pwInput.snp.makeConstraints{
            $0.top.equalTo(pwLabel.snp.bottom).offset(10)
            $0.width.equalTo(scrollView.snp.width).multipliedBy(0.8)
            $0.centerX.equalTo(emailInput)
        }
        
        scrollView.addSubview(pwckLabel)
        pwckLabel.snp.makeConstraints{
            $0.top.equalTo(pwInput.snp.bottom).offset(2)
            $0.leading.equalTo(pwInput.snp.leading)
        }
        
        scrollView.addSubview(pwreLabel)
        pwreLabel.snp.makeConstraints{
            $0.top.equalTo(pwInput.snp.bottom).offset(30)
            $0.leading.equalTo(scrollView.snp.leading).offset(10)
        }
        
        scrollView.addSubview(pwreInput)
        pwreInput.snp.makeConstraints{
            $0.top.equalTo(pwreLabel.snp.bottom).offset(10)
            $0.width.equalTo(scrollView.snp.width).multipliedBy(0.8)
            $0.centerX.equalTo(pwInput)
        }
        
        scrollView.addSubview(pwreckLabel)
        pwreckLabel.snp.makeConstraints{
            $0.top.equalTo(pwreInput.snp.bottom).offset(2)
            $0.leading.equalTo(pwreInput.snp.leading)
        }
        
        scrollView.addSubview(nameLabel)
        nameLabel.snp.makeConstraints{
            $0.top.equalTo(pwreInput.snp.bottom).offset(30)
            $0.leading.equalTo(scrollView.snp.leading).offset(10)
        }
        
        scrollView.addSubview(nameInput)
        nameInput.snp.makeConstraints{
            $0.top.equalTo(nameLabel.snp.bottom).offset(10)
            $0.width.equalTo(scrollView.snp.width).multipliedBy(0.8)
            $0.centerX.equalTo(pwreInput)
        }
        
        scrollView.addSubview(nameckLabel)
        nameckLabel.snp.makeConstraints{
            $0.top.equalTo(nameInput.snp.bottom).offset(2)
            $0.leading.equalTo(nameInput.snp.leading)
        }
        
        scrollView.addSubview(nicknameLabel)
        nicknameLabel.snp.makeConstraints{
            $0.top.equalTo(nameInput.snp.bottom).offset(30)
            $0.leading.equalTo(scrollView.snp.leading).offset(10)
        }
        
        scrollView.addSubview(nicknameInput)
        nicknameInput.snp.makeConstraints{
            $0.top.equalTo(nicknameLabel.snp.bottom).offset(10)
            $0.width.equalTo(scrollView.snp.width).multipliedBy(0.8)
            $0.centerX.equalTo(nameInput)
        }
        
        scrollView.addSubview(nicknameckLabel)
        nicknameckLabel.snp.makeConstraints{
            $0.top.equalTo(nicknameInput.snp.bottom).offset(2)
            $0.leading.equalTo(nicknameInput.snp.leading)
        }
        
        scrollView.addSubview(SignUpbutton)
        SignUpbutton.snp.makeConstraints{
            $0.top.equalTo(nicknameLabel.snp.bottom).offset(50)
            $0.width.equalTo(scrollView.snp.width).multipliedBy(0.8)
            $0.centerX.equalTo(nicknameInput)
        }
        
    }
}

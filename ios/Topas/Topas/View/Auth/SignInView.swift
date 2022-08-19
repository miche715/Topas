//
//  SignIn.swift
//  Topas
//
//  Created by 김경호 on 2022/07/25.
//
import UIKit
import SnapKit

class SignInView : UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    // UI 구성들
    let title = UILabel().then{
        $0.numberOfLines = 3
        $0.text = "Topas 에서\n스터디, 프로젝트\n팀 완성 하세요."
        $0.font = UIFont.boldSystemFont(ofSize: 30)
    }
    
    let idbox = UITextField().then{
        $0.placeholder = "이메일을 입력해주세요."
        $0.textColor = .gray
        $0.backgroundColor = .systemGray6
        $0.layer.cornerRadius = 1
        $0.autocapitalizationType = .none
    }
    
    let pwbox = UITextField().then{
        $0.placeholder = "비밀번호를 입력해주세요."
        $0.textColor = .gray
        $0.textContentType = .password
        $0.isSecureTextEntry = true
        $0.backgroundColor = .systemGray6
        $0.layer.cornerRadius = 1
        $0.autocapitalizationType = .none
    }
    
    let loginFlagLabel = UILabel().then{
        $0.textColor = .red
    }

    let SignInButton = UIButton().then{
        $0.setTitle("로그인", for: .normal)
        $0.setTitleColor(UIColor.white, for: .normal)
        $0.backgroundColor = UIColor(named: "BrandColor")
        
        // 그림자 효과
        $0.layer.shadowColor = UIColor.black.cgColor
        $0.layer.masksToBounds = false
        $0.layer.shadowOffset = CGSize(width: 0, height: 4)
        $0.layer.shadowOpacity = 0.3
        
        $0.layer.cornerRadius = 6
        $0.layer.borderWidth = 1
        $0.layer.borderColor = UIColor.black.cgColor
    }

    let underText = UILabel().then{
        $0.text = "계정을 잊으셨습니까?\t\t\t 또는\n아직 회원이 아니십니까?"
        $0.font = UIFont.systemFont(ofSize: 14)
        $0.numberOfLines = 2
        $0.textColor = .lightGray
        $0.textAlignment = .center
    }

    let findEmailButton = UIButton().then{
        $0.setTitle("이메일 찾기", for: .normal)
        $0.setTitleColor(UIColor(named: "BrandColor"), for: .normal)
        $0.titleLabel?.font = UIFont.systemFont(ofSize: 15)
    }
    
    let findPWButton = UIButton().then{
        $0.setTitle("비밀번호 찾기", for: .normal)
        $0.setTitleColor(UIColor(named: "BrandColor"), for: .normal)
        $0.titleLabel?.font = UIFont.systemFont(ofSize: 15)
    }

    let signupButton = UIButton().then{
        $0.setTitle("회원가입", for: .normal)
        $0.setTitleColor(UIColor(named: "BrandColor"), for: .normal)
        $0.titleLabel?.font = UIFont.systemFont(ofSize: 15)
    }
    
    private func commonInit(){
        self.backgroundColor = .white
        
        self.addSubview(title)
        title.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(50)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(30)
        }
        
        self.addSubview(idbox)
        idbox.snp.makeConstraints{
            $0.top.equalTo(title.snp.bottom).offset(50)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(30)
            $0.height.equalTo(50)
            $0.width.equalTo(self.safeAreaLayoutGuide.snp.width).multipliedBy(0.85)
        }
        
        self.addSubview(pwbox)
        pwbox.snp.makeConstraints{
            $0.top.equalTo(idbox.snp.bottom).offset(10)
            $0.leading.equalTo(idbox.snp.leading)
            $0.height.equalTo(50)
            $0.width.equalTo(idbox.snp.width)
        }
        
        self.addSubview(loginFlagLabel)
        loginFlagLabel.snp.makeConstraints{
            $0.top.equalTo(pwbox.snp.bottom).offset(5)
            $0.leading.equalTo(pwbox.snp.leading)
        }
        
        self.addSubview(SignInButton)
        SignInButton.snp.makeConstraints{
            $0.top.equalTo(pwbox.snp.bottom).offset(40)
            $0.leading.equalTo(pwbox.snp.leading)
            $0.width.equalTo(pwbox.snp.width)
            $0.height.equalTo(40)
        }
        
        self.addSubview(underText)
        underText.snp.makeConstraints{
            $0.top.equalTo(SignInButton.snp.bottom).offset(10)
            $0.leading.equalTo(SignInButton.snp.leading)
        }

        self.addSubview(findEmailButton)
        findEmailButton.snp.makeConstraints{
            $0.top.equalTo(underText.snp.top).offset(-7)
            $0.leading.equalTo(underText.snp.leading).offset(125)
        }

        self.addSubview(findPWButton)
        findPWButton.snp.makeConstraints{
            $0.top.equalTo(underText.snp.top).offset(-7)
            $0.leading.equalTo(underText.snp.leading).offset(230)
        }

        self.addSubview(signupButton)
        signupButton.snp.makeConstraints{
            $0.top.equalTo(underText.snp.top).offset(9)
            $0.leading.equalTo(underText.snp.leading).offset(190)
        }

    }
    
    
}

//
//  OnBoardView.swift
//  Topas
//
//  Created by 김경호 on 2022/07/25.
//
import UIKit
import SnapKit

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
    
    let title = UILabel().then{
        $0.text = "팀을 찾고 계십니까?"
        $0.font = UIFont.boldSystemFont(ofSize: 30)
        $0.textColor = UIColor.white
    }
    
    
    let des1 = UILabel().then{
        $0.text = "Topas에는"
        $0.font = UIFont.systemFont(ofSize: 25)
        $0.textColor = UIColor.white
    }
    
    let des2 = UILabel().then{
        $0.text = "수 많은 팀들이"
        $0.font = UIFont.systemFont(ofSize: 25)
        $0.textColor = UIColor.white
    }
    
    let des3 = UILabel().then{
        $0.text = "당신을 위해 기다립니다."
        $0.font = UIFont.systemFont(ofSize: 25)
        $0.textColor = UIColor.white
    }
    
    private func commonInit(){
        self.addSubview(title)
        title.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(70)
            $0.width.equalTo(300)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(30)
            $0.height.equalTo(50)
        }
        
        self.addSubview(des1)
        des1.snp.makeConstraints{
            $0.top.equalTo(title.snp.bottom).offset(20)
            $0.width.equalTo(title.snp.width)
            $0.leading.equalTo(title.snp.leading)
        }
        
        self.addSubview(des2)
        des2.snp.makeConstraints{
            $0.top.equalTo(des1.snp.bottom)
            $0.width.equalTo(des1.snp.width)
            $0.leading.equalTo(des1.snp.leading)
        }
        
        self.addSubview(des3)
        des3.snp.makeConstraints{
            $0.top.equalTo(des2.snp.bottom)
            $0.width.equalTo(des2.snp.width)
            $0.leading.equalTo(des2.snp.leading)
        }
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
    
    let title = UILabel().then{
        $0.text = "팀원을 찾고 계십니까?"
        $0.font = UIFont.boldSystemFont(ofSize: 30)
        $0.textColor = UIColor.white
    }
    
    let des1 = UILabel().then{
        $0.text = "Topas에는"
        $0.font = UIFont.systemFont(ofSize: 25)
        $0.textColor = UIColor.white
    }
    
    let des2 = UILabel().then{
        $0.text = "수 많은 사람들이"
        $0.font = UIFont.systemFont(ofSize: 25)
        $0.textColor = UIColor.white
    }
    
    let des3 = UILabel().then{
        $0.text = "당신을 위해 기다립니다."
        $0.font = UIFont.systemFont(ofSize: 25)
        $0.textColor = UIColor.white
    }
    
    private func commonInit(){
        self.addSubview(title)
        title.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(70)
            $0.width.equalTo(300)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(30)
            $0.height.equalTo(50)
        }
        
        self.addSubview(des1)
        des1.snp.makeConstraints{
            $0.top.equalTo(title.snp.bottom).offset(20)
            $0.width.equalTo(title.snp.width)
            $0.leading.equalTo(title.snp.leading)
        }
        
        self.addSubview(des2)
        des2.snp.makeConstraints{
            $0.top.equalTo(des1.snp.bottom)
            $0.width.equalTo(des1.snp.width)
            $0.leading.equalTo(des1.snp.leading)
        }
        
        self.addSubview(des3)
        des3.snp.makeConstraints{
            $0.top.equalTo(des2.snp.bottom)
            $0.width.equalTo(des2.snp.width)
            $0.leading.equalTo(des2.snp.leading)
        }
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
    
    let title = UILabel().then{
        $0.text = "Topas 지금 시작하세요."
        $0.font = UIFont.boldSystemFont(ofSize: 30)
        $0.textColor = UIColor.white
    }
    
    
    let signupButton = UIButton().then{
        $0.setTitle("새로운 회원으로 가입하기", for: .normal)
        $0.setTitleColor(UIColor.black, for: .normal)
        $0.backgroundColor = .white
        $0.setTitleColor(UIColor(named: "BrandColor"), for: .normal)
        //그림자
        $0.layer.shadowColor = UIColor.black.cgColor
        $0.layer.masksToBounds = false
        $0.layer.shadowOffset = CGSize(width: 0, height: 4)
        $0.layer.shadowOpacity = 0.3
    }
    
    let signinButton = UIButton().then{
        $0.setTitle("기존 회원으로 시작하기", for: .normal)
        $0.setTitleColor(UIColor(named: "BrandColor"), for: .normal)
        $0.backgroundColor = .white
        //그림자
        $0.layer.shadowColor = UIColor.black.cgColor
        $0.layer.masksToBounds = false
        $0.layer.shadowOffset = CGSize(width: 0, height: 4)
        $0.layer.shadowOpacity = 0.3
    }
    

    
    
    func singinLink(){
        let storyboard = UIStoryboard(name: "SignIn", bundle: nil)
        let signin = storyboard.instantiateViewController(identifier: "SignInView")
    }
    
    
    private func commonInit(){
        self.addSubview(title)
        title.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(70)
            $0.width.equalTo(300)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(30)
            $0.height.equalTo(50)
        }
        
        self.addSubview(signupButton)
        signupButton.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.bottom).offset(-210)
            $0.width.equalTo(self.safeAreaLayoutGuide.snp.width).multipliedBy(0.85)
            $0.centerX.equalTo(self.safeAreaLayoutGuide.snp.centerX)
            $0.height.equalTo(40)
        }
        
        self.addSubview(signinButton)
        signinButton.snp.makeConstraints{
            $0.top.equalTo(signupButton.snp.bottom).offset(10)
            $0.width.equalTo(signupButton.snp.width)
            $0.centerX.equalTo(signupButton.snp.centerX)
            $0.height.equalTo(signupButton.snp.height)
        }

    }
}

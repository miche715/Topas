//
//  OnBoardView.swift
//  ios
//
//  Created by 김경호 on 2022/07/20.
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
    
    private func loadView(){
        
    }
    let vc = UIView()
    
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "팀을 찾고 계십니까?"
        label.frame = CGRect(x: 30, y: 120, width: 250, height: 50)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        label.textColor = UIColor.white
        return label
    }()
    
    
    let des1 : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Topas에는"
        label.frame = CGRect(x: 30, y: 180, width: 150, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des2 : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "수 많은 팀들이"
        label.frame = CGRect(x: 30, y: 210, width: 150, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des3 : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "당신을 위해 기다립니다."
        label.frame = CGRect(x: 30, y: 240, width: 300, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    private func commonInit(){
        //self.backgroundColor = UIColor(red: 0, green: 102, blue: 255, alpha: 1)
        
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
    
    private func loadView(){
        
    }
    let vc = UIView()
    
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "팀원을 찾고 계십니까?"
        label.frame = CGRect(x: 30, y: 120, width: 300, height: 50)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        label.textColor = UIColor.white
        return label
    }()
    
    let des1 : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Topas에는"
        label.frame = CGRect(x: 30, y: 180, width: 200, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des2 : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "수 많은 사람들이"
        label.frame = CGRect(x: 30, y: 210, width: 200, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    let des3 : UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "당신을 위해 기다립니다."
        label.frame = CGRect(x: 30, y: 240, width: 300, height: 50)
        label.font = UIFont.systemFont(ofSize: 25)
        label.textColor = UIColor.white
        return label
    }()
    
    private func commonInit(){
        //self.backgroundColor = UIColor(red: 0, green: 102, blue: 255, alpha: 1)
        
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
    
    private func loadView(){
        
    }
    let vc = UIView()
    
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Topas 지금 시작하세요."
        label.frame = CGRect(x: 30, y: 120, width: 300, height: 50)
        label.font = UIFont.boldSystemFont(ofSize: 30)
        label.textColor = UIColor.white
        return label
    }()
    
    
    
    private func commonInit(){
        //self.backgroundColor = UIColor(red: 0, green: 102, blue: 255, alpha: 1)
        
        self.addSubview(title)
    }
}

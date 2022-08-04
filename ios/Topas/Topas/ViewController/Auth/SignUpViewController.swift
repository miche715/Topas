//
//  SignUpViewController.swift
//  Topas
//
//  Created by 김경호 on 2022/08/01.
//

import UIKit
import SwiftUI
import Then

class SignUp: UIViewController{
    let vc = SignUpView()
    
    let profilePickController = UIImagePickerController()
    
    
    override func viewDidLoad() {
        self.view = vc
        profilePickController.delegate = self
    }
    
    func profileImageAdd(){
        let alert = UIAlertController(title: "프로필 사진 선택", message: "", preferredStyle: .actionSheet)
        
        let library = UIAlertAction(title: "사진앨범", style: .default){
            (action) in self.openLibrary()
        }
        let camera = UIAlertAction(title: "카메라", style: .default){
            (action) in self.openCamera()
        }
        let cancel = UIAlertAction(title: "취소", style: .cancel, handler: nil)
        
        
        alert.addAction(library)
        alert.addAction(camera)
        alert.addAction(cancel)
        present(alert, animated: true, completion: nil)
    }

    func openCamera(){
        if(UIImagePickerController.isSourceTypeAvailable(.camera)){
            profilePickController.sourceType = .camera
            present(profilePickController, animated: false, completion: nil)
        } else {
            print("Camera not available")
        }
    }

    func openLibrary(){
        profilePickController.sourceType = .photoLibrary
        present(profilePickController, animated: false, completion: nil)
    }
}

extension SignUp : UIImagePickerControllerDelegate, UINavigationControllerDelegate{
    
    
}





struct ViewControllerRepresentable: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> SignUp {
        return SignUp()
    }
    
    func updateUIViewController(_ uiViewController: SignUp, context: Context) {
    }
    
    
    typealias UIViewControllerType = SignUp

}

@available(iOS 15.5, *)
struct ViewPreview: PreviewProvider {
    static var previews: some View {
        ViewControllerRepresentable()
    }
}

class SignUpView: UIView{
    let scrollView = UIScrollView().then{
        $0.backgroundColor = .white
        $0.frame = CGRect(x: 50, y: 50, width: 100, height: 100)
        
        $0.translatesAutoresizingMaskIntoConstraints = false
    }
    
    let profileImage = UIImageView().then{
        $0.frame = CGRect(x: 100, y: 100, width: 100, height: 100)
        $0.layer.cornerRadius = $0.frame.width / 2
        $0.clipsToBounds = true
        $0.backgroundColor = .blue
        $0.translatesAutoresizingMaskIntoConstraints = false
    }
    
    let emailLabel = UILabel().then{
        $0.text = "이메일"
        $0.textColor = .blue
        $0.frame = CGRect(x: 5, y: 5, width: 100, height: 100)
        
        $0.translatesAutoresizingMaskIntoConstraints = false
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
         
        scrollView.addSubview(emailLabel)
        
        scrollView.addSubview(profileImage)
        
    }
}

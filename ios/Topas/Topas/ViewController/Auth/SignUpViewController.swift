//
//  SignUpViewController.swift
//  Topas
//
//  Created by 김경호 on 2022/08/01.
//

import UIKit
import SwiftUI
import Then
import SnapKit

class SignUp: UIViewController{
    let vc = SignUpView()
    
    let profilePickController = UIImagePickerController()
    
    
    override func viewDidLoad() {
        self.view = vc
        profilePickController.delegate = self
        imageaddGestureRecognizer()
        
        vc.emailInput.delegate = self
        vc.pwInput.delegate = self
        vc.pwreInput.delegate = self
        vc.nicknameInput.delegate = self
        vc.nameInput.delegate = self
        
        // 패스워드 확인 바뀌었을 떄 값 체크하는 메소드를 추가해준다.
        vc.pwreInput.addTarget(self, action: #selector(setLabelPasswordConfirm(_:)), for: .editingChanged)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        vc.profileImage.layer.cornerRadius = vc.profileImage.frame.width / 2
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
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[UIImagePickerController.InfoKey.originalImage]
            as? UIImage{
            vc.profileImage.image = image
        } else {
            print("사진을 불러올 수 없습니다.")
        }
        dismiss(animated: true, completion: nil)
    }
    
    @objc func tappedUIImageView(_gesture:UITapGestureRecognizer){
        print("tapped")
        profileImageAdd()
    }
    
    func imageaddGestureRecognizer(){
        let tabGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(tappedUIImageView(_gesture:)))
        
        vc.profileImage.addGestureRecognizer(tabGestureRecognizer)
        vc.profileImage.isUserInteractionEnabled = true
    }
    
    
}
extension SignUp : UIImagePickerControllerDelegate, UINavigationControllerDelegate{
    
    
}

extension SignUp: UITextFieldDelegate{
    // 패스워드가 같은지 다른지 확인 후 라벨의 글자를 바꿔준다.
    @objc func setLabelPasswordConfirm(_ sender:Any?){
        let passwordConfirm = vc.pwInput.text
        let password = vc.pwreInput.text
        guard passwordConfirm != "" else {
            vc.pwreckLabel.text = ""
            return
        }
        
        if password == passwordConfirm{
            vc.pwreckLabel.text = "패스워드가 일치합니다."
            vc.pwreckLabel.textColor = .green
        } else {
            vc.pwreckLabel.text = "패스워드가 다릅니다."
            vc.pwreckLabel.textColor = .red
        }
    }
    
    //Text Field에서 Enter 눌렀을 때 포커싱 옮기기
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField{
        case vc.emailInput:
            // 이메일 -> 비밀번호
            vc.pwInput.becomeFirstResponder()
        case vc.pwInput:
            // 비밀번호 -> 비밀번호 재입력
            vc.pwreInput.becomeFirstResponder()
        case vc.pwreInput:
            // 비밀번호 재입력 -> 이름
            vc.nameInput.becomeFirstResponder()
        case vc.nameInput:
            // 이름 -> 닉네임
            vc.nicknameInput.becomeFirstResponder()
        default:
            // 닉네임 -> 회원가입
            vc.SignUpbutton.becomeFirstResponder()
        }
        return false
    }
    
    // 키보드에서 사용자가 키 하나를 눌렀을 때 취해야 할 동작을 지정
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        
        let utf8Char = string.cString(using: .utf8)
        let isBackSpace = strcmp(utf8Char, "\\b")
        
        switch textField{
        case vc.emailInput:
            if string.emailCheck() || isBackSpace == -92 {
                return true
            }
            return false
        case vc.pwInput:
            if string.pwCheck() || isBackSpace == -92 {
                return true
            }
            return false
        case vc.pwreInput:
            print("hello")
        case vc.nameInput:
            print("nameInput")
        default:
            print("nicknameInput")
        }
        return true
        
    }
    

}
extension String{
    // 필터링을 위함
    func emailCheck() -> Bool {
        do{
            
            let regex = try NSRegularExpression(pattern: "^[a-zA-Z1-9@.\\s]$", options: .caseInsensitive)
            if let _ = regex.firstMatch(in: self,options: NSRegularExpression.MatchingOptions.reportProgress, range: NSMakeRange(0, self.count)){
                return true
            }
        }catch{
            return false
        }
        return false
    }
    
    func pwCheck() -> Bool {
        do {
            let regex = try NSRegularExpression(pattern: "^[a-zA-Z1-9@#$%^&*.!?\\s]$", options: .caseInsensitive)
            if let _ = regex.firstMatch(in: self,options: NSRegularExpression.MatchingOptions.reportProgress, range: NSMakeRange(0, self.count)){
                return true
            }
        }catch{
            return false
        }
        return false
    }
}




//struct ViewControllerRepresentable: UIViewControllerRepresentable {
//    func makeUIViewController(context: Context) -> SignUp {
//        return SignUp()
//    }
//
//    func updateUIViewController(_ uiViewController: SignUp, context: Context) {
//    }
//
//
//    typealias UIViewControllerType = SignUp
//
//}
//
//@available(iOS 15.5, *)
//struct ViewPreview: PreviewProvider {
//    static var previews: some View {
//        ViewControllerRepresentable()
//    }
//}
//
//

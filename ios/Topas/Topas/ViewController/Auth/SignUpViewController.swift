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
import FirebaseAuth

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
        vc.SignUpbutton.addTarget(self, action: #selector(signUp), for: .touchUpInside)
        
        vc.emailInput.becomeFirstResponder()
        vc.pwInput.becomeFirstResponder()
        vc.pwreInput.becomeFirstResponder()
        vc.nicknameInput.becomeFirstResponder()
        vc.nameInput.becomeFirstResponder()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        vc.profileImage.layer.cornerRadius = vc.profileImage.frame.width / 2
        
        //키보드 추가
        self.addKeyboardNotifications()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.removeKeyboardNotifications()
    }
    
    func profileImageAdd(){
        let alert = UIAlertController(title: "프로필 사진 선택", message: "", preferredStyle: .actionSheet)
        
        let library = UIAlertAction(title: "사진앨범", style: .default){
            (action) in self.openLibrary()
        }
        let camera = UIAlertAction(title: "카메라", style: .default){
            (action) in self.openCamera()
        }
        let setdefault = UIAlertAction(title: "기본 사진", style: .default){
            (action) in self.setDefault()
        }
        
        let cancel = UIAlertAction(title: "취소", style: .cancel, handler: nil)
        
        alert.addAction(library)
        alert.addAction(camera)
        alert.addAction(setdefault)
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
     
    func setDefault(){
        self.vc.profileImage.image = UIImage(named: "defaultProfile")
        UserDB.setDefaultImage()
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[UIImagePickerController.InfoKey.originalImage]
            as? UIImage{
            vc.profileImage.image = image
            UserDB.setProfileImage()
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
    @objc func signUp(){
        let email = vc.emailInput.text!
        let pw = vc.pwInput.text!
        let pwConfirm = vc.pwreInput.text!
        let name = vc.nameInput.text!
        let nickname = vc.nicknameInput.text!
        
        var flag = true
        
        if email.count <= 0{
            vc.emailckLabel.text = "이메일을 입력해주세요."
            flag = false
        }
        else
        {
            vc.emailckLabel.text = ""
        }
        
        if pw.count <= 0{
            vc.pwckLabel.text = "비밀번호를 입력해주세요."
            flag = false
        }
        else
        {
            vc.pwckLabel.text = ""
        }
        
        if name.count <= 0{
            vc.nameckLabel.text = "이름을 입력해주세요."
            flag = false
        }
        else
        {
            vc.nameckLabel.text = ""
        }
        
        if nickname.count <= 0{
            vc.nicknameckLabel.text = "닉네임을 입력해주세요."
            flag = false
        }
        else
        {
            vc.nicknameckLabel.text = ""
        }
        
        if pw != pwConfirm{
            vc.pwreckLabel.text = "비밀번호가 다릅니다."
            flag = false
        }
        else
        {
            vc.pwreckLabel.text = ""
        }
        
        if flag{
            Auth.auth().createUser(withEmail: email, password: pw){ user, error in
                if user != nil{
                    print("SignUP Success")
                    
                    //Firestore에 데이터 전송
                    if UserDefaults.standard.string(forKey: "profile_photo") == "defaultProfile"{
                        UserDB.signupModel(email : email, name : name, nick_name : nickname, photo : nil)
                    } else {
                        UserDB.signupModel(email: email, name: name, nick_name: nickname, photo: self.vc.profileImage.image)
                    }
                    
                    guard let mainViewController = self.storyboard?.instantiateViewController(withIdentifier: "Main") as? ViewController else { return }
                    mainViewController.modalPresentationStyle = .fullScreen
                    self.present(mainViewController, animated: true, completion: nil)
                } else{
                    print("SignUP Fail")
                    let errorCode = AuthErrorCode.Code(rawValue: error!._code)!
                    print(error)
                    
                    switch errorCode{
                    case .invalidEmail:
                        self.vc.emailckLabel.text = "잘못된 형식의 이메일입니다."
                        self.vc.emailckLabel.textColor = .red
                    case .emailAlreadyInUse:
                        self.vc.emailckLabel.text = "이미 사용중인 이메일입니다."
                        self.vc.emailckLabel.textColor = .red
                    case .weakPassword:
                        self.vc.pwckLabel.text = "취약한 비밀번호입니다."
                        self.vc.pwckLabel.textColor = .red
                    case .wrongPassword:
                        self.vc.pwckLabel.text = "잘못된 형식의 비밀번호입니다."
                        self.vc.pwckLabel.textColor = .red
                    default:
                        print(errorCode)
                    }

                }
                
            }
        }
    }
    
    //키보드 관련한 메소드들
    func addKeyboardNotifications(){
        //키보드가 나타날 때 앱에게 알리는 메서드
        NotificationCenter.default.addObserver(self, selector: #selector(self.keyboardWillShow(_:)), name: UIResponder.keyboardWillShowNotification, object: nil)
        
        //키보드가 사라질 때 앱에게 알리는 메서드
        NotificationCenter.default.addObserver(self, selector: #selector(self.keyboardWillHide(_:)), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    func removeKeyboardNotifications(){
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    //키보드가 나타났다는 알림을 받으면 실행할 메서드
    @objc func keyboardWillShow(_ noti: NSNotification){
        //키보드의 높이만큼 화면을 올려줌
        if let keyboardFrame: NSValue = noti.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue {
            let keyboardRectangle = keyboardFrame.cgRectValue
            let keyboardHeight = keyboardRectangle.height
//            self.view.frame.origin.y -= keyboardHeight
            UIView.animate(withDuration: 0.3, animations: {
                self.view.transform = CGAffineTransform(translationX: 0, y: -keyboardHeight)
            })
            print("hello")
        }
    }
    
    //키보드가 사라졌다는 알림을 받으면 실행할 메서드
    @objc func keyboardWillHide(_ noti: NSNotification){
//        //키보드의 높이만큼 화면을 내려준다.
//        if let keyboardFrame: NSValue = noti.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue {
//            let keyboardRectangle = keyboardFrame.cgRectValue
//            let keyboardHeight = keyboardRectangle.height
//            //self.view.frame.origin.y += keyboardHeight
//            //self.vc.scrollView.frame.origin.y += keyboardHeight
//            self.view.frame.origin.y += keyboardHeight
//        }
        self.view.transform = .identity
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
            if string.pwCheck() || isBackSpace == -92 {
                return true
            }
            return false
        case vc.nameInput:
            if string.nameCheck() || isBackSpace == -92 {
                return true
            }
            return false
        default:
            if string.nickCheck() || isBackSpace == -92 {
                return true
            }
            return false
        }
        return true
        
    }
    

}
extension String{
    // 필터링을 위함
    func emailCheck() -> Bool {
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
    
    func pwCheck() -> Bool {
        do {
            let regex = try NSRegularExpression(pattern: "^[a-zA-Z0-9@#$%^&*.!?]$", options: .caseInsensitive)
            if let _ = regex.firstMatch(in: self,options: NSRegularExpression.MatchingOptions.reportProgress, range: NSMakeRange(0, self.count)){
                return true
            }
        }catch{
            return false
        }
        return false
    }
    
    func nameCheck() -> Bool{
        do {
            let regex = try NSRegularExpression(pattern: "^[가-힣ㄱ-ㅎㅏ-ㅣ]$", options: .caseInsensitive)
            if let _ = regex.firstMatch(in: self,options: NSRegularExpression.MatchingOptions.reportProgress, range: NSMakeRange(0, self.count)){
                return true
            }
        }catch{
            return false
        }
        return false
    }
    
    func nickCheck() -> Bool{
        do {
            let regex = try NSRegularExpression(pattern: "^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9@#$%^&*.!?\\s]$", options: .caseInsensitive)
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

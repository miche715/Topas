//
//  onBoarding.swift
//  ios
//
//  Created by 김경호 on 2022/07/12.
//

import UIKit

class OnBoarding: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(self.respondToSwipeGesture(_:)))
        swipeLeft.direction = UISwipeGestureRecognizer.Direction.left
        self.view.addGestureRecognizer(swipeLeft)
    }

    @objc func respondToSwipeGesture(_ gesture: UIGestureRecognizer) {
        print("First Swipe")
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            switch swipeGesture.direction {
                case UISwipeGestureRecognizer.Direction.left :
                    self.performSegue(withIdentifier: "firstSegue", sender: self)
            default:
                break
            }
        }
    }
}

class OnBoardingSecond: UIViewController{

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(self.respondToSwipeGesture(_:)))
        swipeLeft.direction = UISwipeGestureRecognizer.Direction.left
        self.view.addGestureRecognizer(swipeLeft)
        
        let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(self.respondToSwipeGesture(_:)))
        swipeRight.direction = UISwipeGestureRecognizer.Direction.right
        self.view.addGestureRecognizer(swipeRight)
    }

    @objc func respondToSwipeGesture(_ gesture: UIGestureRecognizer) {
        print("Second Swipe")
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            switch swipeGesture.direction {
            case UISwipeGestureRecognizer.Direction.left :
                self.performSegue(withIdentifier: "secondSegue", sender: self)
            case UISwipeGestureRecognizer.Direction.right :
                self.presentingViewController?.dismiss(animated: true)
            default:
                break
            }
        }
    }
}

class OnBoardingThree: UIViewController{
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(self.respondToSwipeGesture(_:)))
        swipeRight.direction = UISwipeGestureRecognizer.Direction.right
        self.view.addGestureRecognizer(swipeRight)
    }

    @objc func respondToSwipeGesture(_ gesture: UIGestureRecognizer) {
        print("Second Swipe")
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            switch swipeGesture.direction {
            case UISwipeGestureRecognizer.Direction.right :
                self.presentingViewController?.dismiss(animated: true)
            default:
                break
            }
        }
    }
    
    
    @IBAction func SignIn(_ sender: Any) {
        let storyboard = UIStoryboard(name: "SignIn", bundle: nil)
        let signin = storyboard.instantiateViewController(identifier: "SignInView")
        self.present(signin, animated: true)
    }
    @IBAction func SignUp(_ sender: Any) {
        let storyboard = UIStoryboard(name: "SignUp", bundle: nil)
        let signup = storyboard.instantiateViewController(identifier: "SignUpView")
        self.present(signup, animated: true)
    }
}

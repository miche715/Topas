//
//  OnBoardViewController.swift
//  Topas
//
//  Created by 김경호 on 2022/07/25.
//
import UIKit

class OnBoarding: UIViewController {
    
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var pageControl: UIPageControl!

    // View 가져오기
    let onboardone = onboard1()
    
    let onboardtwo = onboard2()
    
    let onboardthree = onboard3().then{
        $0.signupButton.addTarget(self, action: #selector(signUpLink), for: .touchUpInside)
        $0.signinButton.addTarget(self, action: #selector(signInLink), for: .touchUpInside)
    }
    
    @objc func signUpLink(){
        self.performSegue(withIdentifier: "toSignUp", sender: self)
    }
    
    @objc func signInLink(){
        self.performSegue(withIdentifier: "toSignIn", sender: self)
    }
    
    
    //가져온 View에 배열로 선언
    lazy var onboards : [UIView] = [onboardone, onboardtwo, onboardthree]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        scrollView.delegate = self
        view.backgroundColor = UIColor(named: "BrandColor")
        //가져온 View 배열을 scroll view에 넣어 준다.
        scrollView.contentSize.width = self.view.frame.width * CGFloat(3)
        for i in 0..<onboards.count{
            let onbaord = onboards[i]
            var xPosition = self.view.frame.width * CGFloat(i)
            scrollView.addSubview(onbaord)
            onbaord.snp.makeConstraints{
                $0.top.equalTo(self.view.safeAreaLayoutGuide.snp.top)
                $0.leading.equalTo(xPosition)
                $0.height.equalTo(self.view.safeAreaLayoutGuide.snp.height)
                $0.width.equalTo(self.view.safeAreaLayoutGuide.snp.width)
            }
            //onbaord.frame = CGRect(x: xPosition, y: 0, width: self.view.frame.width, height: self.view.frame.height)
            //scrollView.contentSize.width = self.view.frame.width * CGFloat(1 + i)
            
        }
        setPageControl()
    }
    
    //페이지 컨트롤러 값 적용
    private func setPageControl(){
        pageControl.currentPage = 0
        pageControl.numberOfPages = onboards.count
        pageControl.pageIndicatorTintColor = .lightGray
        pageControl.currentPageIndicatorTintColor = .black
    }
}

extension OnBoarding: UIScrollViewDelegate {
    // 스크롤뷰 터치 시 호출
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let value = scrollView.contentOffset.x / scrollView.frame.size.width
        setPageControlSelectedPage(currentPage: Int(round(value)))
    }
    // 파라미터 값을 넘겨준 값을 페이지 컨트롤러 값으로 변경
    private func setPageControlSelectedPage(currentPage : Int){
        pageControl.currentPage = currentPage
    }
}

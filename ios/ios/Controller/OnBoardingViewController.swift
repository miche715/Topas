//
//  onBoarding.swift
//  ios
//
//  Created by 김경호 on 2022/07/12.
//

import UIKit

class OnBoarding: UIViewController, UIScrollViewDelegate {
    
    
    @IBOutlet weak var pageControl: UIPageControl!
    @IBOutlet weak var scrollView: UIScrollView!
    
    let onboardone : onboard1 = {
        let view = onboard1()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    let onboardtwo : onboard2 = {
        let view = onboard2()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    let onboardthree : onboard3 = {
        let view = onboard3()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    lazy var onboards : [UIView] = [onboardone, onboardtwo, onboardthree]
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
        for i in 0..<onboards.count{
            let onbaord = onboards[i]
            var xPosition = self.view.frame.width * CGFloat(i)
            onbaord.frame = CGRect(x: xPosition, y: 0, width: self.view.frame.width, height: self.view.frame.height)
            scrollView.contentSize.width = self.view.frame.width * CGFloat(1 + i)
            scrollView.addSubview(onbaord)
        }
        setPageControl()
    }
    
    private func setPageControl(){
        pageControl.currentPage = 0
        pageControl.numberOfPages = onboards.count
        pageControl.pageIndicatorTintColor = .lightGray
        pageControl.currentPageIndicatorTintColor = .black
    }
    
    
    private func setPageControlSelectedPage(currentPage : Int){
        pageControl.currentPage = currentPage
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let value = scrollView.contentOffset.x / scrollView.frame.size.width
        setPageControlSelectedPage(currentPage: Int(round(value)))
    }
    
}





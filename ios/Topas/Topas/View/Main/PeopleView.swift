//
//  people.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import SnapKit
import UIKit
import SwiftUI

class PeopleView : UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        commoninit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commoninit()
    }

    let navigationBar = UINavigationBar().then{
        $0.isTranslucent = false
        $0.backgroundColor = .systemBackground
    }
    
    let navItem = UINavigationItem(title: "팀원 모집")

    let tableView = UITableView()
    
    private func commoninit(){
        self.backgroundColor = .white
        
        self.addSubview(navigationBar)
        navigationBar.setItems([navItem], animated: true)
        

        navigationBar.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading)
            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing)
        }
        
        self.addSubview(tableView)
        tableView.snp.makeConstraints{
            $0.top.equalTo(navigationBar.snp.bottom)
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading)
            $0.height.equalTo(self.safeAreaLayoutGuide.snp.height)
            $0.width.equalTo(self.safeAreaLayoutGuide.snp.width)
            $0.bottom.equalTo(self.safeAreaLayoutGuide.snp.bottom)
        }
        
    }
}




//struct ViewControllerRepresentable: UIViewControllerRepresentable {
//
//    typealias UIViewControllerType = People
//
//    func makeUIViewController(context: Context) -> People {
//        return People()
//    }
//
//    func updateUIViewController(_ uiViewController: People, context: Context) {
//    }
//}
//
//@available(iOS 15.5.0, *)
//struct ViewPreview: PreviewProvider {
//    static var previews: some View {
//        ViewControllerRepresentable()
//    }
//}
//

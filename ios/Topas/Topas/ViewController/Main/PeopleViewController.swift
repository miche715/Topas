//
//  people.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import UIKit
import SnapKit

class People : UIViewController{


    let vc = PeopleView()

    var dataSources = [MainCellModel]()

    let tagList: [String] = [
        "이창희",
        "김경호",
        "코딩노예",
        "살려줘"
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view = vc
  
        vc.tableView.estimatedRowHeight = UITableView.automaticDimension
        
        setupView()
        loadData()
    }

    private func setupView() {
        vc.tableView.register(MainListCell.self, forCellReuseIdentifier: MainListCell.identifier)
        vc.tableView.delegate = self
        vc.tableView.dataSource = self
        
    }

    private func loadData() {
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "나는야 주스 될 거야 나는야 케챱 될거야 나는야 춤을 출 거야 멋쟁이 토마토 토마토 또르르륵 또르르륵 빗방울이 내린다. 또르르륵 또르르륵 또르르륵 또르륵", nickname: "Huko", skills: ["Swift", "Python"]))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "죽여버리겠어", nickname: "이", skills: ["JS", "SQL","Swift","JavaScript","Java","Kotlin","Python","C","C#","C++","Unity","Hello","박준형개새키","Test"]))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "나는 기집애 마인드", nickname: "승", skills: ["SQL", "JS"]))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "나는 경호한정 녹턴 궁", nickname: "동", skills: ["Kotlin"]))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "나는 버러지", nickname: "두", skills: ["Unity", "Python"]))
        vc.tableView.reloadData()
    }
}

extension People : UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        print("---------------------------------------------dataSources")
        print(dataSources.count)
        return dataSources.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: MainListCell.identifier) as? MainListCell ?? MainListCell()
        cell.bind(model: dataSources[indexPath.row])
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}
//extension People : UICollectionViewDelegate, UICollectionViewDataSource{
//    // cell 개수
//    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        print("---------------------------------------------tagList")
//        print(tagList.count)
//        return tagList.count
//    }
//    
//    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
//        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "TagCell", for: indexPath) as? TagCell ?? TagCell()
//        cell.tagLabel.text = tagList[indexPath.row]
//        return cell
//    }
//}
//extension People : UICollectionViewDelegateFlowLayout{
//    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
//
//      let label = UILabel().then {
//          $0.font = .systemFont(ofSize: 14)
//          $0.text = tagList[indexPath.item]
//          $0.sizeToFit()
//      }
//      let size = label.frame.size
//
//      return CGSize(width: size.width + 16, height: size.height + 10)
//    }
//}

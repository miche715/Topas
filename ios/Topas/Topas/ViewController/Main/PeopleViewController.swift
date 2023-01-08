//
//  people.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import UIKit
import SnapKit
import FirebaseFirestore

class People : UIViewController{


    let vc = PeopleView()

    var dataSources = [MainCellModel]()
    var testData : [MainCellModel] = []
    
    let db = MainDB()
    
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
        
        //DB값 가져오는데 시간이 걸려서 딜레이를 걸어둠
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
            print("Main View DB Get")
            self.dataSources = MainDB.getUserList()
            self.vc.tableView.reloadData()
        }
    }
}

extension People : UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
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

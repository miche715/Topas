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
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "자기 소개", nickname: "Huko"))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "내 소개", nickname: "이"))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "내 소개", nickname: "승"))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "내 소개", nickname: "동"))
        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "내 소개", nickname: "두"))
        vc.tableView.reloadData()
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

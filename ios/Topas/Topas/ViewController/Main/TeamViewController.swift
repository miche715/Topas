//
//  team,.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import UIKit

class Team : UIViewController{
    
    let vc = TeamView()
    
    var dataSources = [MainTeamModel]()
    
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
//        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "나는야 주스 될 거야 나는야 케챱 될거야 나는야 춤을 출 거야 멋쟁이 토마토 토마토 또르르륵 또르르륵 빗방울이 내린다. 또르르륵 또르르륵 또르르륵 또르륵", nickname: "경호와 함께하는 두근두근 노예선", skills: ["Swift", "Python"]))
//        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "죽.어", nickname: "이승호의 수원대 탈출 졸작 노예선", skills: ["JS", "SQL","Swift","JavaScript","Java","Kotlin","Python","C","C#","C++","Unity","Hello","박준형개새키","Test"]))
//        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "윈스(주) 가입 가능", nickname: "준형이는 탈출하고 싶다.", skills: ["SQL", "JS"]))
//        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "토마토 오피스텔 자유이용권 드립니다.", nickname: "성두와 함꼐하는 유니티", skills: ["Unity", "Python"]))
//        dataSources.append(.init(profile: UIImage(named: "defaultProfile")!, introduce: "JAVA 선착순 3", nickname: "노예선", skills: ["JAVA"]))
        //DB값 가져오는데 시간이 걸려서 딜레이를 걸어둠
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
            print("Main View Team DB Get")
            self.dataSources = MainDB.getTeamList()
            self.vc.tableView.reloadData()
        }
        vc.tableView.reloadData()
    }
}

extension Team : UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        print("---------------------------------------------dataSources")
        print(dataSources.count)
        return dataSources.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: TeamListCell.identifier) as? TeamListCell ?? TeamListCell()
        cell.bind(model: dataSources[indexPath.row])
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}

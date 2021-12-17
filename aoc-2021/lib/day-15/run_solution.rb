require_relative 'solution'

# frozen_string_literal: true

INPUT = '
7467923161199853975163878964955815881991642676938912451447919792683728694766149341499879987416299819
1791159221998515888739659622119122992926373186498969624468691386695488683281672918635142232198939239
9485796811924495714722441818193791389792933213587924999989769968213287794239372869154239239897583649
2277171525886579114528199488879687862967391988729261336278173814179663423317697525489998786347371356
5195198122685417872851127867992769199992846753794591274915576194235188612911897191347158517543132458
5528988966778787947222383999159759459512949498249162943841189213212264641728819565232171821719183265
9197995784994779441992994899747267962283997769392989943298137186286928633189399578778595676775991898
9318376781179933889996869919144219298394191271269688232471268559692365995242897871491179779396591232
1131179665787351498828631494911728523115731515991324929274418153691347887697171385279989996899793838
7132287641992637178133178369839428886729897119458138992889197216327118459114425368918989998491881145
8436821592166369496739987796398111794514362987278867877996998924161928185399713175651261697368284527
1765327983318898923278827517386997251364224195697813592834213984999391327732949918297933494815777649
1395928353367958499818519368299396195193789944864788999121364191758663539619717989981839218119449118
8548779278873913897452975951489752999298697863719992238985923817838779398622395439981151196181899974
5468185148346195247538193779295971287894619995232718978877876251758778531978999295992452689953959982
7599579292685854189981212899187999252981276929539881361295172752318887537954651941713999311872461513
8219199299324361819566399581492131714937472466573413675777371951399152986116956392219684692214129231
2689276998717213196219959933241512123476297929897151983811189888923794789128691779796995913299283684
3699189968917989492695787473972112459669955881798982392319982978738436121358892125368991383974614152
7744936679299911689162989989793999494216358817998993871699522683861695941873231461369171397498191188
4316983492891617867898217981887363799138514724187569836485743472845856993173588961885911165782949454
7999699188761833736725442929979725411111628586769153111971382771784995378233399911929734739924216925
1451292487139826385541713486696936818511559699949741819689293289969977912839415219668715262594663325
5519235872632146591847579649599871758346511495723611919999915942411414997997718948999961961144149991
2131868225276152174247791438139633116179581893623991487877819983271236813764296983115359646789176973
3489525356856591784895968399114791974474337991921499198989526827419998199129819192799183995661898956
2289279875956936315947959711738699965847522956768298898612424291997219928912138816787996786724412118
3881624971932989674698788837998656943971291991774996672658355891829499154579994974117948977197998717
6422624925988939566198613917255897125874841999267849687989349846889939638169999812611981588876692929
1234473949247998712141742955872486991915852589617133979678189614694251713178783851695681176119436696
6598898112516528392199997978252141965286211327294764238637936498694565773499318942931994331923216123
3182791872441893511846399149859625746192481117391919152169183611795817719966227714159113182861618371
9943999986166971319313836956196363128954818794182998293121955939699814869192878462994964599561554897
8783649193164687898971289881789491584867197891181289389928969886329876219651535614796919914398948149
5989782394623139839159919825518832412875194399469897177918837789819598519794969398139989473759879997
9131969637791291686179465311915832531188381398358792994912649299431184293343191997659321569399882194
8911639959226762965391897111418832769579134949926836721238153193691118686984799399531689797859755171
1581697831569899193992912812491813221988777426988971197921347695662391368711629998149771925374691688
8291254332914711161599973686939717992781958174985184458772282895391159929849977315224473729968193254
5923293598894839619712199197619979995892569998338611949916129984948992359479699989148157891369836968
3942651674891892119988997162388999867266474892947897739213946288256951729618899187914164539973947238
6899971424122839798679882983761251616628668585539889271579272449397224799968279977981913963866677272
6613986619929816764941681616296193172544177361959124791817889897841989858694183249984713915698874529
6791698674617967384763662277699912181979895431316973679276925111554988161428882852419699169229457919
7359495829918339441998991842299821693237271797969488593129947826922197699746892919998181615561538681
4652457746289279819916595612228536416158172915928122671962481982884829959423511299194128119935814198
5291992499897759761886499111588138883948486797661921666917837581129994391367229863199964498292757985
5431927863129281956919459317919929921281988172785256931986772939795948494393325985929128297735889789
1591446181859329718867289998375227329219636851174952985897181686692395218595988273237991821276361519
6191135791812999261598263992529191398497318188238998943993297917997888669318617444646693831858141841
7927993123859897636651882848791762818197429961639687731825667385818857838965364591279629379991883728
5415139781398147199842914492698855952712211579357481629195598654631795523119989952519899359734991988
5952722979569893391279567691656951297981369116789279732999335864725847593721912968299311969965158968
1787329867158119699672119521775231717122493479927151125724698979411618974324918196183595129488116166
3829228611732916811648864248942188319862796894172919978798294785429618921971385693529817779281115181
7873623491992813883439811966996765646811568927919992391265875892896789987878853978862718999757589893
4772991499999181531199911938982556949888375819714885961919279999518159661325838395593183219723134799
3899118851149643619396999175882297871997574898129912219952949985192218199655389242618739929913988271
7893499888882251847724616291243796981811738126149187961373758825175819928893153819413152346248921589
9563946398319918598913873499814999486996467497998192199187328187999265592524313489473682886414681118
1586891599388775218269596926113532147999386795142617569878919976667189559844945796716449859979168982
5836438191839227738838982198396898265921584159945976195888789268548697593783957279813661758135147579
9214543112194445127987788264351872121193569199915987332929319942699923923265226256132968679993322913
1893334977491112919868819884411856911991782893139911921643918557968439395632296584298738973494996938
9567192845779417511329821499199667963888879669779686462172942781299789161889451298279699167681651763
9794789656517119148919155179758223587189511891215178594275539933384598352936269149381678847178917714
8984731969123197524968811487996144679899131149955158289112638917942519327525819789626867975996445915
4754458665891129939399898998129455372919918991874115815819459398219884899726297752993555795513819929
5696929176239491186858269221998892781289249673891468997618817264191292386881471987931283587979238725
9697649495498481499383159478619713474263567899959899113855342194992599911549656934819971987698215814
1385976319819996891231219177117561981493288867843289788599958739117979126821824239146962539112253831
6731931372491989212416315355249981886752363871844177679699827891885199886799117228921257999671358589
4683268496419722953994165918218813823628517681887459541351843891437484391594252889827977319293119981
3338998719973993849549992291136298869419444181717863657878679868298197268649288667679569945173279784
3711779719938996918518744152749467668998349292791469838791515156656373272949671413161917619782794799
3999878359919697929882991729551145969996197189235663321549288139821419189998593919983598412421918769
6959944559898996385959983892778359162267963445151982373969988929257929541137189939936914899921919292
4296156896863798989477756412819972897941511947657891579712881998413812291992431149472318277361257213
1482781426639697932199724314111793182478517871598151759565572799924529882195996891713374911961536881
2215719547813726339386919191549475191471752389991121594999786875769981475121499931191429667121431995
9978879313195198898193133799148193388697321393998899285898918294291237999823879399847127838129534942
2799311116832177918921693964931639717719528497632739319819781951983193988593299888277145417849174315
6391897598471945779119339913799411296152998761969489469921839481199889312971929986332128397622484491
8989789629921993795218865983699411182171887165937981779819152539992515921486488472439587949699616292
3532779712957181988869963999318592275889254911411594669919691313183453188763898991237195196212459914
9891599793437297177136434861931498516928675965944942797479797819912997766829914137119148119792923836
9119112413346833221991229399999959293598899289919979193694995251127179964999898444111134557623181719
9438196299918979264998243891757538227165313894291566926925882268297641926115198763379199983982147994
1151992689379717889976452915654494646813879976589216942999974179298915978976356378949972249626277455
9326958492398589918818418741557499714954617499668492843151296779363989485479951712849616884597792896
2187594179686799999117155279891521599518141184949198779489179195674616191145717111192164698895997549
6945418792279826966811627646627899751722939791598187879897288494136981197862485876516171587984359736
9144887865519196371631428578912179181195414275474192934919851111688135697949923164794972889358686381
4991999264899216986985813319249612962784721979413784962999719144957175269221352555826696799316221422
6894115187165137798369985938192111674939265794411819979168316797699872569963962954975439873817399397
9929991819167512996985128992875212478118416768189385136598678928399953741632822759749739695936948348
3493122317848392594667199761631592561618817115979982529429312171978976166899778461278399149828886899
6982725264715915411989427982199991687513965378711439962956247499641488133199718817957269692939673179
9999478422468217991289896971319159799529511886742239745859781319654169919913856917468872561861712919
2798217219738855755749522266326446838229933717751932193589926835391162658998953169476898614839119719
'.freeze

solution = Day15.new

puts 'Solution for Day 15:'
puts format('---> Part 1: %s', solution.solve_first(INPUT))
puts format('---> Part 2: %s', solution.solve_second(INPUT))

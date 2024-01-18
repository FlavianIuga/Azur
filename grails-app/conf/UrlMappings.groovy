class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/login/$action"(controller: "login")
		"/logout/$action"(controller: "logout")
		
		"/"(view:"/actiune/cautare")
		"500"(view:'/error')
		
		"/"(controller:"actiune/cautare")
		"/index.gsp"(controller:"actiune/cautare")
	}
}

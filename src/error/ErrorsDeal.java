package error;

public   enum ErrorsDeal {
    ERRORS_GENERALE("100", " generale errors"),
    INVALID_CLIENT("101","Pour étre bénéficier au deal  vous devez  inscrire."),
    CHAMPS_OBLIGATOIRE("102","Veuillez remplir le champ obligatoire."),
    CIN_FORMAT_ERROR("103","Verifier votre cin !"),
    Tel_FORMAT_ERROR("103","Verifier votre numéro de téléphone !"),
    EMAIL_FORMAT_ERROR("103","Verifier votre mail !"),
    CLIENT_NOT_FOUND("104","Ce client n'est pas existe."),
    CLIENT_NOT_HAVE_DEAL("105","Ce client n'est pas inscrit  a aucun deal."),
    CLIENT_INSCRIT("105","Vous êtes déjà inscrit."),
    DEAL_NOT_EXISTE("105"," deal n'est pas existant ."),
    DEAL_NOT_VEND("105","Aucun client achète ce produit.");



    private String error, desc;

    public String getError() {
        return error;
    }

    public String getDesc() {
        return desc;
    }

    private ErrorsDeal(String error, String desc) {
        this.error = error;
        this.desc = desc;
    }

}
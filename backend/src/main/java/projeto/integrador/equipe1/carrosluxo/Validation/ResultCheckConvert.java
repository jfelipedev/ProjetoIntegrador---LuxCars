package projeto.integrador.equipe1.carrosluxo.Validation;

import java.util.Date;

public class ResultCheckConvert {
    private Date startDate;
    private Date endDate;

    public ResultCheckConvert(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
